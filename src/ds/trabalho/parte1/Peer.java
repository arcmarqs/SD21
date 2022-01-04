package ds.trabalho.parte1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Peer {

    public static void main(String[] args) throws Exception {
        System.out.printf("new peer @ host=%s\n", args[0]);
        new Thread(new Server(args[0], Integer.parseInt(args[1]))).start();
        new Thread(new Client()).start();
    }
}

class Server implements Runnable {
    static boolean lock = false;
    ServerSocket server;

    public Server(String host, int port) throws Exception {
        server = new ServerSocket(port, 1, InetAddress.getByName(host));
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
        //prepare socket I/O channels
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            
            //parse command
            Scanner sc = new Scanner(in.readLine());
            int token = Integer.parseInt(sc.next());
            sc.close();
            
            while(Server.lock);

            //send result
            out.println(String.valueOf(token + 1));
            out.flush();

            // close connection
            clientSocket.close();
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
            
            System.out.print("$ ");
            
            switch(scanner.nextLine()) {
                case "lock()":
                    Server.lock = true;
                    break;
                case "unlock()":
                    Server.lock = false;
                    break;
            }
        }
    }
}