import java.net.InetAddress;
import java.net.UnknownHostException;

public class NodeMonitor {

    public NodeMonitor(String node_id) {
        Sender sender = new Sender(node_id);
        Thread sender_thread = new Thread(sender);
        sender_thread.start();
        Listener listener = new Listener(node_id);
        NodeList node_list = new NodeList(node_id);
        listener.addObserver(node_list);
        new NodeWindow(node_list);
        Thread listener_thread = new Thread(listener);
        listener_thread.start();
    }

    public static void main(String[] args) {
        String hostname = "";
        try {
            hostname = args[0];
            new NodeMonitor(hostname);
        } catch (ArrayIndexOutOfBoundsException ae) {
            try {
                hostname = InetAddress.getLocalHost().getHostName() + " (" + InetAddress.getLocalHost().getHostAddress() + ")";
            } catch (UnknownHostException e) {
                System.err.println("Host name could not be determined.");
                System.exit(-1);
            }
        } finally {
            new NodeMonitor(hostname);
        }
    }
}
