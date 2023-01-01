import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import paradigmlims.Configs.*;

public class paradigmLIMS {

    public static String VERSION = "0.2";

    public static void main(String[] args) {
        if (args.length > 0) {
            if (args[0].equals("-h")) {
                usageMessage();
            } else if (args[0].equals("-v")) {
                System.out.println("\n\nVersion: " + VERSION + "\n");
            } else if (args[0].equals("-c")) {
                paradigmConfig cf = new paradigmConfig(paradigmConfig.staticFilePath());
                cf.setInConsole(true);
                ParadigmViews.generalConsole c = new ParadigmViews.generalConsole(cf);
            }
        } else {
            ParadigmViews.loginGUI gui = new ParadigmViews.loginGUI();
            gui.launchFrame();
        }
    }

    public static void usageMessage() {
        System.out.println("\n\nUsage: \n" + "-h       Print this help message\n" + "-c       Console mode\n" + "-v       Print the version\n");
    }
}
