import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SetLBF {

    public static void main(String[] args) {
        try {
            byte[] buf = new byte[2048];
            Options options = new Options(args);
            int port = 2004;
            if (options.hasOption("-port")) {
                port = Integer.parseInt(options.getOption("-port"));
            }
            String group = options.getOption("-group");
            InetAddress groupAddr = InetAddress.getByName(group);
            BloomFilter filter = new BloomFilter(options.getParameter(0));
            DatagramSocket socket = new DatagramSocket();
            int index = 0;
            buf[index++] = 1;
            System.arraycopy(groupAddr.getAddress(), 0, buf, index, 4);
            index += 4;
            System.arraycopy(filter.getValue(), 0, buf, index, BloomFilter.SIZE);
            index += BloomFilter.SIZE;
            DatagramPacket packet = new DatagramPacket(buf, 0, index, InetAddress.getByName("127.0.0.1"), port);
            socket.send(packet);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
