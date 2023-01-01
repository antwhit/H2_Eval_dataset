import java.io.*;
import java.net.*;
import org.javasock.*;
import org.javasock.sockets.UDPSocket;
import org.javasock.sockets.UDPSocketFactory;

/**
  * Test program to evaluate javasock based UDP socket.
  */
public class UDPChat {

    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            System.err.println("UDPChat host port localport");
            return;
        }
        String remoteHost = args[0];
        int remotePort = Integer.parseInt(args[1]), localPort = Integer.parseInt(args[2]);
        OSIDataLinkDevice device = OSIDataLinkDevice.getDevices()[0];
        InetAddress localAddr = null;
        {
            UDPSocketFactory sockfac = new UDPSocketFactory(device, null);
            DatagramSocket.setDatagramSocketImplFactory(sockfac);
        }
        try {
            final DatagramSocket sock = new DatagramSocket(localPort, localAddr);
            DatagramPacket toSend = new DatagramPacket(new byte[0], 0, InetAddress.getByName(remoteHost), remotePort);
            device.startCapture();
            System.err.println("Connected!  Enter lines to send.  Hit ^Z to end.");
            new Thread(new Runnable() {

                public void run() {
                    try {
                        DatagramPacket dg = new DatagramPacket(new byte[1500], 1500);
                        while (true) {
                            sock.receive(dg);
                            System.out.print(new String(dg.getData(), 0, dg.getLength()));
                        }
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
            }).start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line;
            while ((line = reader.readLine()) != null) {
                toSend.setData((line + '\n').getBytes());
                sock.send(toSend);
            }
            sock.close();
        } finally {
            device.stopCapture();
        }
    }
}
