import java.util.Vector;
import nativ.NativeException;
import nativ.RawSendTask;
import nativ.RawSocket;
import net.Data2xml;
import net.Xml2Data;
import nativ.NativeException;
import nativ.RawSocket;
import net.Xml2Data;
import nativ.EthSocket;

public class rawsend {

    public static void main(String[] a) {
        Vector<String> send_value = new Vector<String>();
        Vector<String> send_type = new Vector<String>();
        Vector<String> send_length = new Vector<String>();
        send_type.add("bit");
        send_length.add("4");
        send_value.add("4");
        send_type.add("bit");
        send_length.add("4");
        send_value.add("5");
        send_type.add("byte");
        send_length.add("1");
        send_value.add("0");
        send_type.add("byte");
        send_length.add("2");
        send_value.add("51");
        send_type.add("byte");
        send_length.add("2");
        send_value.add("1");
        send_type.add("bit");
        send_length.add("3");
        send_value.add("0");
        send_type.add("bit");
        send_length.add("13");
        send_value.add("0");
        send_type.add("byte");
        send_length.add("1");
        send_value.add("128");
        send_type.add("byte");
        send_length.add("1");
        send_value.add("1");
        send_type.add("byte");
        send_length.add("2");
        send_value.add("0x5fc0");
        send_type.add("ip4address");
        send_length.add("4");
        send_value.add("192.168.110.2");
        send_type.add("ip4address");
        send_length.add("4");
        send_value.add("212.92.25.40");
        send_type.add("byte");
        send_length.add("1");
        send_value.add("8");
        send_type.add("byte");
        send_length.add("1");
        send_value.add("0");
        send_type.add("byte");
        send_length.add("2");
        send_value.add("0xdb4f");
        send_type.add("byte");
        send_length.add("2");
        send_value.add("0");
        send_type.add("byte");
        send_length.add("2");
        send_value.add("0");
        send_type.add("text");
        send_length.add("23");
        send_value.add("ping_123456789123456789");
        rawsend.sendEndRecvMessage(send_type, send_length, send_value);
    }

    public static void sendEndRecvMessage(Vector<String> send_type, Vector<String> send_length, Vector<String> send_value) {
        try {
            Xml2Data d2x_send = new Xml2Data(send_type, send_length, send_value);
            RawSocket rs = new RawSocket(nativ.RawSocket.PF_INET);
            rs.send(d2x_send.getData(), d2x_send.getSrcAddr(), d2x_send.getDestAddr());
            System.out.println("sendMessage");
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
