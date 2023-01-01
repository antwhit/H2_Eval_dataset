import java.util.Vector;
import nativ.*;
import net.*;

public class recv_one {

    public static void main(String[] a) {
        simpleReceive();
    }

    public static void simpleReceive() {
        Vector<String> value;
        Vector<String> type;
        Vector<String> length;
        value = new Vector<String>();
        type = new Vector<String>();
        length = new Vector<String>();
        Vector<String> recv_value = new Vector<String>();
        Vector<String> recv_type = new Vector<String>();
        Vector<String> recv_length = new Vector<String>();
        recv_type.add("bit");
        recv_length.add("4");
        recv_value.add("4");
        recv_type.add("bit");
        recv_length.add("4");
        recv_value.add("5");
        recv_type.add("byte");
        recv_length.add("1");
        recv_value.add("0");
        recv_type.add("byte");
        recv_length.add("2");
        recv_value.add("51");
        recv_type.add("byte");
        recv_length.add("2");
        recv_value.add("any");
        recv_type.add("bit");
        recv_length.add("3");
        recv_value.add("0");
        recv_type.add("bit");
        recv_length.add("13");
        recv_value.add("0");
        recv_type.add("byte");
        recv_length.add("1");
        recv_value.add("ANY");
        recv_type.add("byte");
        recv_length.add("1");
        recv_value.add("1");
        recv_type.add("byte");
        recv_length.add("2");
        recv_value.add("ANY");
        recv_type.add("ip4address");
        recv_length.add("4");
        recv_value.add("ANY");
        recv_type.add("ip4address");
        recv_length.add("4");
        recv_value.add("any");
        recv_type.add("byte");
        recv_length.add("1");
        recv_value.add("0");
        recv_type.add("byte");
        recv_length.add("1");
        recv_value.add("0");
        recv_type.add("byte");
        recv_length.add("2");
        recv_value.add("any");
        recv_type.add("byte");
        recv_length.add("2");
        recv_value.add("0");
        recv_type.add("byte");
        recv_length.add("2");
        recv_value.add("0");
        recv_type.add("text");
        recv_length.add("23");
        recv_value.add("ping_123456789123456789");
        try {
            int eth_number = 1;
            boolean promiscus = false;
            EthSocket eth = new EthSocket(eth_number, promiscus);
            eth.settimeout(3000);
            Xml2Data d2x = new Xml2Data(recv_type, recv_length, recv_value);
            byte[][] filter = new byte[2][];
            filter[0] = d2x.getData();
            filter[1] = d2x.getMask();
            boolean ethheader = false;
            byte[] recv_packet = eth.recvfilterone(filter, ethheader);
            System.out.print("recv_packet: " + recv_packet.length);
            Vector<String> recv_string = (new Data2xml(recv_type, recv_length, recv_packet)).getData();
            for (int i = 0; i < recv_string.size(); i++) {
                System.out.print(recv_type.get(i) + "  ");
                System.out.println(recv_string.get(i));
            }
            eth.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
