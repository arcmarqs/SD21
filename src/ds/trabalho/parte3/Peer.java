package ds.trabalho.parte3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Peer {

    static Long myTimestamp;
    
    static HashSet<InetAddress> clients = new HashSet<>();
    
    // ler o ip ou hostanme o meu neigh
    // ler o ip ou hostanme do seguinto

    public static void main(String[] args) throws Exception {
        myTimestamp = System.currentTimeMillis();
        
        for(int i = 1; i < args.length; i++){
            clients.add(InetAddress.getByName(args[i]));
        }

        new Thread(new Server(args[0])).start();
        new Thread(new Client()).start();
    }
}

class Server implements Runnable {
    ServerSocket server;
    static final int PORT = 54547;

    public Server(String myHost) throws Exception {
        this.server = new ServerSocket(Server.PORT, 1, InetAddress.getByName(myHost));
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
}

class Connection implements Runnable {
    Socket clientSocket;

    public Connection(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // parse command
            Scanner sc = new Scanner(in.readLine());
            Long clientTimestamp = Long.parseLong(sc.next());
            String message = sc.nextLine();
            sc.close();

            //Peer.clients.put(clientSocket.getInetAddress(),
            //    Math.max(clientTimestamp, Peer.myTimestamp) + 1);

            Peer.myTimestamp = Math.max(Peer.myTimestamp, clientTimestamp) + 1;

            /*tc = max(tc, ts) + 1;
            // bleat to everyone
            if (the message received is not itself a bleat) {
                foreach client q {
                    net-send(q,bleat,tc);
                }
            }
            
            put (m,ts) in a sorted queue;
            while ( “something from everyone” in the queue) {
                P = fetch and remove earliest timestamp message in queue;
                if P isn't a bleat, deliver(P);
            }*/

            // close connection
            clientSocket.close();

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
        while (true) {
            String message = scanner.nextLine();
            Peer.timestampClient += 1;

            for (String client : Peer.clients.keySet()) {
                netSend(client, message, Peer.timestampClient);
            }
        }
    }
}