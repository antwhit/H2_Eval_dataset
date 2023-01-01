import java.awt.AWTException;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.microedition.xlet.*;
import sun.awt.Robot;

public class ContextClsLoaderXlet implements Xlet {

    private RoundButton b;

    private boolean done = false;

    private static RuntimeException t;

    public void initXlet(XletContext context) {
        outputContextLoader("initXlet()");
        b = new RoundButton("press me");
        b.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (!done) {
                    outputContextLoader("actionPerformed()");
                    done = true;
                }
            }
        });
        try {
            Container c = context.getContainer();
            c.add(b);
            b.setSize(100, 100);
            c.setSize(100, 100);
            c.setLocation(10, 10);
            c.validate();
            c.setVisible(true);
            c.paint(c.getGraphics());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void startXlet() {
        outputContextLoader("startXlet()");
        try {
            Robot robot = new Robot();
            if (!done) {
                Point loc = b.getLocationOnScreen();
                loc.x += (b.getWidth() / 2);
                loc.y += (b.getHeight() / 2);
                robot.mouseMove(loc.x, loc.y);
                robot.delay(100);
                robot.mousePress(MouseEvent.BUTTON1_MASK);
                robot.mouseRelease(MouseEvent.BUTTON1_MASK);
            }
        } catch (AWTException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public void pauseXlet() {
        outputContextLoader("pauseXlet()");
    }

    public void destroyXlet(boolean unconditional) {
        outputContextLoader("destroyXlet()");
    }

    private static void outputContextLoader(String where) {
        ClassLoader contextLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(where + ": context class loader = " + contextLoader);
        if (!(contextLoader instanceof com.sun.xlet.XletClassLoader) && (t == null)) {
            t = new RuntimeException(where + " unexpected context class loader: " + contextLoader.toString() + " " + Thread.currentThread());
            System.err.println("FAILED: " + t.getMessage());
            throw t;
        }
    }
}
