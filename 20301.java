/**
 * <p>A simple server that echoes UDP packets to the console
 * and via UDP back to the source IP and port. If you have netcat
 * on your system try the following command to see the echo working too:</p>
 *
 * <pre>nc -u localhost 1234</pre>
 *
 * @author Robert Harder
 * @author rharder@users.sourceforge.net
 * @version 0.1
 * @see UdpServer
 */
public class UdpEchoExample {

    public static void main(String[] args) throws Exception {
        int port = 1234;
        try {
            port = Integer.parseInt(args[0]);
        } catch (Exception exc) {
            System.out.println("No port, or bad port, provided. Will use " + port);
        }
        UdpServer us = new UdpServer();
        us.setPort(port);
        us.addUdpServerListener(new UdpServer.Listener() {

            @Override
            public void packetReceived(UdpServer.Event evt) {
                System.out.println(evt.getPacketAsString());
                try {
                    evt.send(evt.getPacket());
                } catch (java.io.IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        us.start();
        System.out.println("Server started on port " + port);
    }
}
