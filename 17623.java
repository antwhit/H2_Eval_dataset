import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSend {

    public static void main(String args[]) {
        if (args.length != 1) {
            System.out.println("Invalid.....");
            System.out.println("Usage: java UDPSend [msg]");
            return;
        }
        try {
            String host = "127.0.0.1";
            int port = 9001;
            String strMsg = "<msg>" + args[0] + "</msg>";
            byte[] message = strMsg.getBytes();
            byte[] buffer = new byte[2048];
            InetAddress address = InetAddress.getByName(host);
            DatagramPacket packet = new DatagramPacket(message, message.length, address, port);
            System.out.println("[UDP] Send a message : " + args[0]);
            DatagramSocket dsocket = new DatagramSocket();
            dsocket.send(packet);
            dsocket.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
