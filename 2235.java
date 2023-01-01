import quickfix.*;
import java.io.FileInputStream;

public class Executor {

    private static Acceptor acceptor = null;

    static {
        System.loadLibrary("quickfix_jni");
    }

    public static void main(String args[]) throws Exception {
        if (args.length != 1) {
            System.out.println("usage: run_executor_java FILE.");
            return;
        }
        try {
            Application application = new Application();
            SessionSettings settings = new SessionSettings(new FileInputStream(args[0]));
            MessageStoreFactory messageStoreFactory = new FileStoreFactory(settings);
            LogFactory logFactory = new ScreenLogFactory(true, true, true);
            MessageFactory messageFactory = new DefaultMessageFactory();
            acceptor = new SocketAcceptor(application, messageStoreFactory, settings, logFactory, messageFactory);
            acceptor.start();
            System.out.println("press <enter> to quit");
            System.in.read();
            acceptor.stop();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
