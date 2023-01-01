import java.io.IOException;
import net.tinyos.message.*;
import net.tinyos.packet.*;
import net.tinyos.util.*;

public class PrintfClient implements MessageListener {

    private MoteIF moteIF;

    public PrintfClient(MoteIF moteIF) {
        this.moteIF = moteIF;
        this.moteIF.registerListener(new PrintfMsg(), this);
    }

    public void messageReceived(int to, Message message) {
        PrintfMsg msg = (PrintfMsg) message;
        for (int i = 0; i < msg.totalSize_buffer(); i++) {
            char nextChar = (char) (msg.getElement_buffer(i));
            if (nextChar != 0) System.out.print(nextChar);
        }
    }

    private static void usage() {
        System.err.println("usage: PrintfClient [-comm <source>]");
    }

    public static void main(String[] args) throws Exception {
        String source = "";
        if (args.length == 2) {
            if (!args[0].equals("-comm")) {
                usage();
                System.exit(1);
            }
            source = args[1];
        } else {
            usage();
            System.exit(1);
        }
        PhoenixSource phoenix;
        if (source == null) {
            phoenix = BuildSource.makePhoenix(PrintStreamMessenger.err);
        } else {
            phoenix = BuildSource.makePhoenix(source, PrintStreamMessenger.err);
        }
        System.out.print(phoenix);
        MoteIF mif = new MoteIF(phoenix);
        PrintfClient client = new PrintfClient(mif);
    }
}
