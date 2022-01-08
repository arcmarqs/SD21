package ds.trabalho.parte3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;

public class Peer {

    static final AtomicLong myTimestamp = new AtomicLong();
    static final HashSet<InetAddress> neighbors = new HashSet<>();

    public static void main(String[] args) throws Exception {
        Peer.myTimestamp.set(System.currentTimeMillis());
        
        for(int i = 1; i < args.length; i++){
            Peer.neighbors.add(InetAddress.getByName(args[i]));
        }

        new Thread(new Server(args[0])).start();
        new Thread(new Client()).start();
    }
}

class Server implements Runnable {
    private ServerSocket server;
    public static final int PORT = 54547;

    static final PriorityQueue<Message> pq = new PriorityQueue<Message>();

    public Server(String myHost) throws Exception {
        this.server = new ServerSocket(Server.PORT, 1, InetAddress.getByName(myHost));
    }

    public boolean isBleat(Message message) {
        return pq.contains(message);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket clientSocket = server.accept();

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                Message message = new Message().parse(in.readLine());
                clientSocket.close();

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
                    Client.mSend(tc, message.getMessage());
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
}

class Client implements Runnable {
    private Scanner scanner;

    public Client() throws Exception {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run() {
        while (true) {
            Client.mSend(scanner.nextLine());
        }
    }

    public static void deliver(Message message) {
        System.out.println(message);
    }

    public static void mSend(Message message) {
        for (InetAddress client : Peer.neighbors) {
            try {
                message.sendTo(client);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void mSend(String input) {
        long timestamp = Peer.myTimestamp.incrementAndGet();
        Message message = new Message(timestamp, input);
        mSend(message);
    }

    public static void mSend(Long timestamp, String line) {
        Message message = new Message(timestamp, line);
        mSend(message);
    }
}

class Message {
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

    public void sendTo(InetAddress client) throws Exception {
        Socket socket = new Socket(client, Server.PORT);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        out.println(this.toString());
        out.flush();

        socket.close();
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
        return timestamp + " " + message;
    }
        
}