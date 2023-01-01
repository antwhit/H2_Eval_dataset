import java.nio.channels.SocketChannel;
import java.util.GregorianCalendar;

/**
 * @author Markus Plessing
 */
public class SocketTest {

    private static GregorianCalendar startCal = new GregorianCalendar();

    private static GregorianCalendar endCal;

    /**
     * @param ip the ip to connect to
     * @param port the port to connect to
     * 
     */
    public SocketTest(String ip, int port) {
        try {
            SocketChannel sChannel = SocketChannel.open();
            sChannel.configureBlocking(false);
            sChannel.connect(new java.net.InetSocketAddress(ip, port));
            while (!sChannel.finishConnect()) {
            }
            sChannel.close();
            System.err.println("IP and Port available.");
        } catch (Exception e) {
            System.err.println("IP and Port unreachable.");
        }
    }

    private static void calculateTime(GregorianCalendar cal) {
        int shour = cal.get(GregorianCalendar.HOUR);
        int smin = cal.get(GregorianCalendar.MINUTE);
        int ssec = cal.get(GregorianCalendar.SECOND);
        int sms = cal.get(GregorianCalendar.MILLISECOND);
        System.err.println(checkZero(shour) + ":" + checkZero(smin) + ":" + checkZero(ssec) + ":" + checkZero(sms));
    }

    private static String checkZero(int checkVal) {
        String retVal = "";
        if (("" + checkVal).length() < 2) {
            retVal += "0" + checkVal;
        } else retVal = "" + checkVal;
        return retVal;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        if (args.length == 2) {
            System.err.print("Start ");
            calculateTime(startCal);
            new SocketTest(args[0], new Integer(args[1]).intValue());
            endCal = new GregorianCalendar();
            System.err.print("End   ");
            calculateTime(endCal);
        } else {
            System.err.println("Usage: java SocketTest <IP-address> <port>");
            System.exit(0);
        }
    }
}
