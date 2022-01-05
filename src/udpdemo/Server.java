package udpdemo;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT = 9999;

    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws Exception {
        ServerSocket listener = new ServerSocket(PORT);

        while (true) {
            System.out.println("Waiting for client connection...");
            Socket client = listener.accept();
            System.out.println("Connected to Client! with Ip " + client.getInetAddress() + " and Port of " + client.getPort());
            System.out.println("Total clients connected " + (clients.stream().count() + 1));
            ClientHandler clientThread = new ClientHandler(client, clients, client.getPort());
            clients.add(clientThread);

            pool.execute(clientThread);
        }

    }
}
