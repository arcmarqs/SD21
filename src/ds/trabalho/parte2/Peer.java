package ds.trabalho.parte2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Peer {

    // ler o ip ou hostanme o meu
    // ler o ip ou hostanme do seguinto

    public static void main(String[] args) throws Exception {
        System.out.printf("new peer @ host=%s\n", args[0]);
        new Thread(new Server(args[0], args[1])).start();
        new Thread(new Client()).start();
    }
}

class Server implements Runnable {
    static final int PORT = 54545;
    ServerSocket server;
    
    static boolean lock = false;

    HashMap<String,InetAddress> iptable = new HashMap<>();
    HashMap<String,String> dictionary = new HashMap<>(); 
    
    static String nextHost;
    public Server(String myHost, String nextHost) throws Exception {
        this.server = new ServerSocket(Server.PORT, 1, InetAddress.getByName(myHost));
        Server.nextHost = nextHost;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket client = server.accept();
                new Thread(new Connection(client)).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void register(String target){
        InetAddress ip = InetAddress.getByName(target);

        iptable.insert(target,ip);

        
    }


}

class Connection implements Runnable {
    Socket clientSocket;

    public Connection(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        // prepare socket I/O channels
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // parse command
            Scanner sc = new Scanner(in.readLine());
            sc.close();

            // close connection
            clientSocket.close();
            
            System.out.println("Token");

            if(!Server.lock) {
                Server.unlock();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Client implements Runnable {
    Scanner scanner;

    public Client() throws Exception {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run() {
        String host;
        while (true) {

            System.out.print("$ ");

            String line = scanner.nextLine();
            if(line.contains("register(") && ){
                host = line.substring(8, line.length() - 1);
                Server.register(host);
            } else if(line.contains("push(")) {
                host = line.substring(5,line.length()-1);
            } else if(line.contains("pull(")) {
                host = line.substring(5,line.length()-1);
            } else if(line.contains("pushpull(")) {
                host = line.substring(9,line.length()-1);
            }

        }
    }
}