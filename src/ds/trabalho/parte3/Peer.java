package ds.trabalho.parte3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Peer {

    // ler o ip ou hostanme o meu
    // ler o ip ou hostanme do seguinto

    public static void main(String[] args) throws Exception {
        new Thread(new Server(args[0], args[1])).start();
        new Thread(new Client()).start();
    }
}

class Server implements Runnable {
    static final int PORT = 54545;

    ServerSocket server;

    static boolean lock = false;

    static int token = 0;
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

    public static void lock() {
        Server.lock = true;
    }

    public static void unlock() {
        try {
            System.out.println("Token " + Server.token);
            
            Socket socket = new Socket(InetAddress.getByName(nextHost), Server.PORT);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            out.println(String.valueOf(Server.token + 1));
            out.flush();

            socket.close();

            Server.lock = false;
        } catch (Exception e) {
            e.printStackTrace();
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
            Server.token = Integer.parseInt(sc.next());
            sc.close();

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
            switch (scanner.nextLine()) {
                case "lock()":
                    Server.lock();
                    break;
                case "unlock()":
                    Server.unlock();
                    break;
            }
        }
    }
}