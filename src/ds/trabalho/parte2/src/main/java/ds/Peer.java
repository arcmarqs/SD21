package ds;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.net.InetAddress;
import java.net.UnknownHostException;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Peer {
    public static void main(String[] args) throws Exception {
        new Thread(new PServer(args[0])).start();
    }    
}

class PServer implements Runnable {
    Server server;    
    int port=52502;
    Scanner scanner;
    private MessageGrpc.MessageBlockingStub stub;
    private ManagedChannel chan;
    static String hostName;
    static Map<String,String> dict = new HashMap<>();
    static HashSet<String> hostNames = new HashSet<>();

    public PServer(String host) throws IOException {
        PServer.hostName = host;
        scanner = new Scanner(System.in);
        start();
    }

    void start() throws IOException {
        server = ServerBuilder.forPort(port).addService(new MessageImpl()).build().start();
        refreshDict();
        buildChannel();
        stub = MessageGrpc.newBlockingStub(chan);
        System.out.println("Server started on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.err.println("JVM shut down");
                try {
                    destroyChannel();
                    PServer.this.stop();
                } catch(InterruptedException e) {
                    e.printStackTrace(System.err);
                }
            }
        });
    }

    @Override
    public void run() {
        while (true) {
            String targetHost = "null";
            String line = scanner.nextLine();

            try {
               
                targetHost = line.substring(line.indexOf("(")+1,line.indexOf(")"));

                if(line.contains("register(")){
                    register(targetHost);
                    
                } else if(line.contains("push(")) {
                    push(targetHost);
                } else if(line.contains("pull(")) {
                    pull(targetHost);
                } else if(line.contains("pushpull(")){
                    pushpull(targetHost);
                } else {
                    System.out.println("Unknown command");
                }
            } catch (Exception e){
                    System.err.println("unknown Command exeption " + e );
            }
        }        
    }

    private void stop() throws InterruptedException {
        if(server != null) {
            server.shutdown().awaitTermination(30,TimeUnit.SECONDS);
        }
    }

    public static void registerHost(String host) {
        hostNames.add(host);
    }

    private static void updateDict(Map<String,String> otherdict) {
        dict.putAll(otherdict);
    }


    private static void refreshDict() {
        try {
            PServer.dict.putAll(DataParser.getRandomEntries());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public static void printHostList() {
        System.out.println(hostNames.toString());

    }

    public static void printDictionary() {
        System.out.println(dict.toString());
    }

    public static String getHostName() {
        return hostName;
    }
    private void buildChannel(){
        chan = ManagedChannelBuilder.forAddress(hostName, port).usePlaintext().build();
    }
    private void destroyChannel() throws InterruptedException{
        chan.shutdownNow();
    } 

    public void setTargetHost(String target) {
        chan.enterIdle();
        chan = ManagedChannelBuilder.forAddress(target, port).usePlaintext().build();
    }

    String hostToIP(String h) {
        String ip = "null";

        try {
            ip = InetAddress.getByName(h).toString();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ip;
    }

    void register(String host){

        PServer.hostNames.add(host);
        setTargetHost(host);
        MessageRequest r = MessageRequest.newBuilder().setHost(hostName).build();
        stub.register(r);
        chan.enterIdle();
        System.out.println(host + " registered" );
        printHostList();
    }

    void pull(String host) {

        if(!PServer.hostNames.contains(host)) {
            System.err.println("Host " + host + " not registered");
            return;
        }

        setTargetHost(host);
        MessageRequest r = MessageRequest.newBuilder().setHost(PServer.hostName).build();
        Dictionary rep = stub.pull(r);
        chan.enterIdle();
        PServer.dict.putAll(rep.getDictMap());
        System.out.println("Updated Dictionary with info from " + host);
        printDictionary();
    }

    void push(String host) {

        if(!PServer.hostNames.contains(host)) {
            System.err.println("Host " + host + " not registered");
            return;
        }

        setTargetHost(host);
        Dictionary r = Dictionary.newBuilder().putAllDict(PServer.dict).build();
        stub.push(r);
        chan.enterIdle();
        System.out.println("Sent Dictionary to " + host);
    }

    void pushpull(String host){

        if(!PServer.hostNames.contains(host)) {
            System.err.println("Host " + host + " not registered");
            return;
        }

        setTargetHost(host);
        Dictionary d = Dictionary.newBuilder().putAllDict(PServer.dict).build();
        PPRequest r = PPRequest.newBuilder().setHost(PServer.hostName).setD(d).build();
        Dictionary rep = stub.pushpull(r);
        chan.enterIdle();
        PServer.dict.putAll(rep.getDictMap());
        System.out.println("Updated Dictionary with info from " + host);
        printDictionary();
    }

    static class MessageImpl extends MessageGrpc.MessageImplBase {

        @Override
        public void pull(MessageRequest request, StreamObserver<Dictionary> responseObserver) {
            String h = request.getHost();
            if(!PServer.hostNames.contains(h)){
                System.err.println("Host " + h + " not registered");
                responseObserver.onCompleted();
            } else {
            responseObserver.onNext(Dictionary.newBuilder().putAllDict(PServer.dict).build());
            responseObserver.onCompleted();

            System.out.println("Updated Dictionary with info from" + h);
            printDictionary();
            }
        }

        @Override
        public void push(Dictionary request, StreamObserver<Empty> responseObserver) {
            PServer.dict.putAll(request.getDictMap());
            responseObserver.onNext(Empty.newBuilder().build());
            responseObserver.onCompleted();
        }

        @Override
        public void pushpull(PPRequest request, StreamObserver<Dictionary> responseObserver) {
            String h = request.getHost();
            if(!PServer.hostNames.contains(h)){
                System.err.println("Host " + h + " not registered");
                responseObserver.onCompleted();
            } else {
            responseObserver.onNext(Dictionary.newBuilder().putAllDict(PServer.dict).build());
            PServer.dict.putAll(request.getD().getDictMap());
            responseObserver.onCompleted();
            System.out.println("Exchanged Dictionaries with" + h);
            }
        }

        @Override
        public void register(MessageRequest request, StreamObserver<Empty> responseObserver) {
            String h = request.getHost();
            PServer.hostNames.add(h);
            responseObserver.onNext(Empty.newBuilder().build());
            responseObserver.onCompleted();
            System.out.println(h + " registered" );
            refreshDict();
            printHostList();
        }
    }
}
