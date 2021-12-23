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
            
            String op = sc.next();
            double x = Double.parseDouble(sc.next());
            double y = Double.parseDouble(sc.next());
            
            //close scanner
            sc.close();
            
            double result = 0.0;
            // execute op
            switch (op) {
                case "add":
                    result = x + y;
                    break;
                case "sub":
                    result = x - y;
                    break;
                case "mul":
                    result = x * y;
                    break;
                case "div":
                    result = x / y;
                    break;
            }
            
            //send result
            out.println(String.valueOf(result));
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
            try {
                //read command
                System.out.print("$ ");
                String server = scanner.next();
                String port = scanner.next();
                String command = scanner.nextLine();

                // make connection
                Socket socket = new Socket(InetAddress.getByName(server), Integer.parseInt(port));

                //prepare socket I/O channels
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // send command
                out.println(command);
                out.flush();

                // receive result
                String result = in.readLine();
                System.out.printf("= %f\n", Double.parseDouble(result));

                // close connection
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}