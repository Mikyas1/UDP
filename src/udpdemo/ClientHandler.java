package udpdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private ArrayList<ClientHandler> clients;
    private int port;

    public ClientHandler(Socket clientSocket, ArrayList<ClientHandler> clients, int port) throws IOException {
        this.client = clientSocket;
        this.clients = clients;
        this.in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
        this.out = new PrintWriter(this.client.getOutputStream(), true);
        this.port = port;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String request = this.in.readLine();
                if (request.contains("STOP")) {
                    System.out.println("Closing Connection with client");
                    this.clients.remove(this);
                    break;
                }
                else {
                    sendToAll(this.port + " says: " + request);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.out.close();
            try {
                this.in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendToAll(String mes) {
        for (ClientHandler ch : clients) {
            ch.out.println(mes);
        }
    }
}
