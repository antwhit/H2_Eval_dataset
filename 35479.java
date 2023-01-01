import com.teletalk.jserver.*;

public class SendServer {

    public static volatile boolean testComplete = false;

    public static JServer server = null;

    public static void main(String args[]) {
        try {
            System.out.println("Creating SendServer! (" + JServer.getVersionString() + ")");
            createServer();
            System.out.println("SendServer started!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createServer() {
        try {
            server = new JServer("SendServer");
            SendController sendController = new SendController(server);
            server.addSubSystem(sendController);
            server.startJServer();
            while (true) {
                Thread.sleep(60000 + (long) (60000 * Math.random()));
                if (!testComplete) {
                    System.out.println("Killing SendController");
                    sendController.shutDown();
                    sendController.waitForDown(60000);
                    Thread.sleep(5000);
                    if (!testComplete) {
                        System.out.println("Starting SendController");
                        sendController.engage();
                        sendController.waitForEnabled(60000);
                        System.out.println("SendController running");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startService(String args[]) {
        createServer();
    }

    public static void stopService() {
        if (server != null) server.shutDown();
    }
}
