import com.notmacchallenge.*;
import javax.swing.*;
import java.io.*;

public class NotMac implements Runnable {

    String[] args;

    public NotMac(String[] args) {
        this.args = args;
    }

    public void run() {
        try {
            if (args != null && args.length > 0) {
                System.setProperty("java.awt.headless", "true");
                if (args[0].equals("uninstall")) {
                    NMCommon.disableNotMac();
                    try {
                        Runtime.getRuntime().exec("open MoveToTrash.app", null, new File("/Library/Application Support/NotMac/Applescript/"));
                    } catch (Exception ee) {
                    }
                    Thread.sleep(500);
                    System.exit(0);
                } else if (args[0].equals("httpTunnel")) {
                    HttpTunnel.startTunnel();
                } else if (args[0].equals("create")) {
                } else if (args[0].equals("delete")) {
                } else if (args[0].equals("configure")) {
                } else if (args[0].equals("password")) {
                }
            } else {
                ClientSetup.launch();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(new NotMac(args)).start();
    }
}
