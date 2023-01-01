import org.quickfix.*;
import java.io.FileInputStream;

public class at {

    static {
        System.loadLibrary("quickfix_jni");
    }

    public static void main(String[] args) throws Exception {
        String file = new String();
        boolean threaded = false;
        if (args.length >= 2 && args[0].equals("-f") && args[1] != null) {
            file = args[1];
        } else {
            System.out.println("usage: at" + " -f FILE [-t]");
            return;
        }
        SessionSettings settings = new SessionSettings(new FileInputStream(file));
        at_application application = new at_application();
        FileStoreFactory factory = new FileStoreFactory(settings);
        Acceptor acceptor = new SocketAcceptor(application, factory, settings, new DefaultMessageFactory());
        acceptor.start();
        while (true) Thread.sleep(1000);
    }
}
