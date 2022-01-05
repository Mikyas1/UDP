package udpdemo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) throws Exception {

        DatagramSocket ds = new DatagramSocket();

        int i = 8;
        byte[] b = (i + "").getBytes();
        System.out.println(b.length);
        InetAddress ia = InetAddress.getLocalHost();
        DatagramPacket dp = new DatagramPacket(b, b.length, ia, 9999);
        ds.send(dp);

        byte[] ret = new byte[1024];
        DatagramPacket result = new DatagramPacket(ret, ret.length);
        ds.receive(result);

        String str = new String(result.getData());
        System.out.println("Server says " + str.trim());
    }
}
