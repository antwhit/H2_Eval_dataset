import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class DelayDatagram {

    static List<DelayDatagram> datagrams = null;

    long fireMillis;

    DatagramPacket packet = null;

    /**
	 * The socket to use
	 */
    DatagramSocket socket;

    DelayDatagram(DatagramSocket socket, DatagramPacket request) {
        if (datagrams == null) datagrams = new LinkedList<DelayDatagram>();
        this.socket = socket;
        if (decodeRequest(request)) {
            datagrams.add(this);
        }
    }

    Boolean decodeRequest(DatagramPacket request) {
        long delay = 0;
        fireMillis = System.currentTimeMillis();
        String orgRequest = new String(request.getData(), 0, request.getLength());
        String lines[] = orgRequest.split("\r\n");
        for (String line : lines) {
            String args[] = line.split(":", 2);
            String cmd = args[0].trim();
            if (cmd.equals("id") && args.length > 1) {
                byte sendData[] = line.getBytes();
                packet = new DatagramPacket(sendData, sendData.length, request.getAddress(), request.getPort());
                continue;
            }
            if (cmd.equals("delay") && args.length > 1) {
                delay = Long.parseLong(args[1].trim());
                fireMillis = System.currentTimeMillis() + delay;
                continue;
            }
            if (cmd.equals("OPTIONS") && args.length > 1) {
                delay = 0;
                fireMillis = System.currentTimeMillis() + delay;
                continue;
            }
        }
        return (packet != null);
    }

    /** How long to to delay until next fire time
	 * @return long delay
	 */
    public static long delay() {
        long min = Long.MAX_VALUE;
        if (datagrams != null) {
            for (DelayDatagram dd : datagrams) {
                if (dd.fireMillis < min) min = dd.fireMillis;
            }
        }
        return min - System.currentTimeMillis();
    }

    /** Time to fire
	 * @return long delay
	 */
    public Boolean expired() {
        return (System.currentTimeMillis() - fireMillis >= 0);
    }

    /**
	 * Send the prepared datagram and remove from list
	 */
    public void fire() {
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        datagrams.remove(this);
    }

    /**
	 * Send the prepared datagram
	 */
    public void fireOnly() {
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
	 * Walk thru all datagrams and send those
	 * whose timer expired.
	 * Remove any fired from list.
	 */
    public static void fireAtWill() {
        if (datagrams != null) {
            DelayDatagram dd;
            Iterator<DelayDatagram> it = datagrams.iterator();
            while (it.hasNext()) {
                dd = it.next();
                if (dd.expired()) {
                    dd.fireOnly();
                    it.remove();
                }
            }
        }
    }
}
