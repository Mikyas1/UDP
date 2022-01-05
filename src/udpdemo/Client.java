package udpdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 9999;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(SERVER_IP, SERVER_PORT);

        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        ServerConnection sc = new ServerConnection(socket);
        new Thread(sc).start();

        while (true) {
            System.out.println("> ");
            String command = keyboard.readLine();

            out.println(command);
            if (command.equals("STOP")) break;

        }
        keyboard.close();
        out.close();
    }
}
