import java.net.*;

public class XMPPSessionListener {

    private static ServerSocket _listeningSock;

    private static Thread _listenerThread = new Thread() {

        public void run() {
            try {
                while (true) {
                    Socket connection = _listeningSock.accept();
                    new XMPPLinkLocalChatSession(connection);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    };

    public static void startListening(int portNr) {
        try {
            _listeningSock = new ServerSocket(portNr);
            _listenerThread.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
