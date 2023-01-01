import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import org.xnap.commons.gui.ErrorHandler;
import org.xnap.commons.util.AWTExceptionHandler;
import org.xnap.commons.util.UncaughtExceptionManager;

/**
 * 
 * @author Steffen Pingel
 */
public class UncaughtExceptionManagerExample extends JFrame {

    private static UncaughtExceptionManager manager;

    /**
	 * 
	 */
    public UncaughtExceptionManagerExample() {
        JButton button = new JButton("Throw Exception!");
        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                throw new RuntimeException("Unexpected Exception");
            }
        });
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(button, BorderLayout.CENTER);
        pack();
    }

    public static void internalMain(String[] args) {
        UncaughtExceptionManagerExample app = new UncaughtExceptionManagerExample();
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
        app.setTitle("XNap-Commons - " + UncaughtExceptionManagerExample.class);
        app.setVisible(true);
    }

    public static void main(final String[] args) {
        manager = new UncaughtExceptionManager();
        ErrorHandler handler = new ErrorHandler(null, manager, null, null);
        manager.addExceptionListener(handler);
        ThreadGroup tg = new ThreadGroup("AppThreadGroup") {

            public void uncaughtException(Thread t, Throwable e) {
                manager.uncaughtException(t, e);
            }
        };
        AWTExceptionHandler.install();
        Thread mainRunner = new Thread(tg, "JHylaFAXMain") {

            public void run() {
                setContextClassLoader(UncaughtExceptionManagerExample.class.getClassLoader());
                internalMain(args);
            }
        };
        mainRunner.start();
    }
}
