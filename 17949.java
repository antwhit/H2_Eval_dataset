import java.awt.*;
import java.awt.event.*;

class ExtendedFrame extends Frame {

    private static boolean a = true;

    private boolean b = a;

    public ExtendedFrame() {
        addWindowListener(c);
        a = false;
    }

    public ExtendedFrame(String title) {
        super(title);
        addWindowListener(c);
        a = false;
    }

    WindowAdapter c = new WindowAdapter() {

        public void windowClosing(WindowEvent e) {
            dispose();
            if (b) System.exit(0);
        }
    };
}
