package ds.trabalho.parte3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class Peer {

    static final AtomicLong myTimestamp = new AtomicLong();
    static final ArrayList<String> neighbors = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        Peer.myTimestamp.set(System.currentTimeMillis());

        for(int i = 1; i < args.length; i++){
            Peer.neighbors.add(args[i]);
        }

        new Thread(new Server(args[0])).start();
        new Thread(new Client()).start();
    }
}

class Server implements Runnable {
    private ServerSocket server;
    private InetAddress serverIp;
    public static final int PORT = 54547;

    static final PriorityQueue<Message> pq = new PriorityQueue<>();
    static final HashMap<InetAddress, ArrayList<Message>> source = new HashMap<>();

    public Server(String myHost) throws Exception {
        this.server = new ServerSocket(Server.PORT, 1, InetAddress.getByName(myHost));
        this.serverIp = server.getInetAddress();
    }

    public void addMessage(InetAddress ip, Message message){
        if(! source.containsKey(ip)){
            source.put(ip, new ArrayList<Message>());
        }
        
        ArrayList<Message> messages = source.get(ip);
        messages.add(message);
    }

    public boolean somethingFromEveryone() {
        // garantir que tem uma mensagem de cada maquina na priority queue.
        for (Message message : pq) {
            
        }
        
        return true;
    }

    public boolean isBleat(Message message) {
        return pq.contains(message);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket clientSocket = server.accept();

                InetAddress sender = clientSocket.getInetAddress();

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                Message message = new Message().parse(in.readLine());
                clientSocket.close();
                
                addMessage(sender, message);

                //tc = max(tc, ts) + 1;
                long tc;
                for(;;) {
                    long oldValue = Peer.myTimestamp.get();
                    tc = Math.max(oldValue, message.getTimestamp()) + 1;
                    if(Peer.myTimestamp.compareAndSet(oldValue, tc))
                        break;
                }
                
                /* bleat to everyone
                if (the message received is not itself a bleat) {
                    foreach client q {
                        net-send(q,bleat,tc);
                    }
                }*/

                if(! isBleat(message)) {
                    Server.mSend(tc, message.getMessage());
                }

                /*put (m,ts) in a sorted queue;
                while ( “something from everyone” in the queue) {
                    P = fetch and remove earliest timestamp message in queue;
                    if P isn't a bleat, deliver(P);
                }*/

                Server.pq.add(message);
                while(! Server.pq.isEmpty()) {
                    Message p = Server.pq.poll();
                    
                    if(! isBleat(p) )
                        Client.deliver(p);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void mSend(Message message) {
        for (String client : Peer.neighbors) {
            try {
                sendTo(InetAddress.getByName(client), message);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void mSend(String input) {
        long timestamp = Peer.myTimestamp.incrementAndGet();
        mSend(new Message(timestamp, input));
    }

    public static void mSend(Long timestamp, String line) {
        mSend(new Message(timestamp, line));
    }

    public static void sendTo(InetAddress client, Message message) throws Exception {
        Socket socket = new Socket(client, Server.PORT);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        out.println(message.toString());
        out.flush();

        socket.close();
    }
}

class Client implements Runnable {
    private Scanner scanner;

    public Client() throws Exception {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run() {
        while (true) {
            String input = scanner.nextLine();
            Server.mSend(" " + input);
        }
    }

    public static void deliver(Message message) {
        System.out.println(message);
    }
}

class Message implements Comparable<Message> {
    private Long timestamp;
    private String message;

    public Message() {}

    public Message(Long timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }

    public Message parse(String in) {
        Scanner sc = new Scanner(in);
        this.timestamp = Long.parseLong(sc.next());
        this.message = sc.nextLine();
        sc.close();
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int compareTo(Message m) {
        if(this.timestamp > m.timestamp) {
            return 1;
        } else if(this.timestamp < m.timestamp){
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Message other = (Message) obj;
        if (message == null) {
            if (other.message != null)
                return false;
        } else if (!message.equals(other.message))
            return false;
        return true;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return timestamp + message;
    }
        
}