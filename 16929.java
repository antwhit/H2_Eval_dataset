import javax.microedition.xlet.*;
import com.sun.xlet.*;

public class TestXlet implements Xlet, Runnable {

    XletContext context;

    boolean started = false;

    public void initXlet(XletContext context) {
        this.context = context;
    }

    public void startXlet() {
        if (!started) {
            started = true;
            new Thread(this).start();
        }
    }

    public void pauseXlet() {
        System.out.println("pauseXlet called");
    }

    public void destroyXlet(boolean b) {
        System.out.println("destroyXlet called");
    }

    public void run() {
        System.out.println("Starting the test");
        try {
            Thread.sleep(500);
        } catch (Exception e) {
        }
        int state = XletManager.getState(context);
        if (state != 3) {
            System.out.println("Test failed, expecting 3 (active), received " + state);
            return;
        }
        context.notifyPaused();
        try {
            Thread.sleep(500);
        } catch (Exception e) {
        }
        state = XletManager.getState(context);
        if (state != 2) {
            System.out.println("Test failed, expecting 2 (paused), received " + state);
            return;
        }
        context.resumeRequest();
        try {
            Thread.sleep(500);
        } catch (Exception e) {
        }
        state = XletManager.getState(context);
        if (state != 2 && state != 3) {
            System.out.println("Test failed, expecting 2 (paused) or 3 (active), received " + state);
            return;
        }
        context.notifyDestroyed();
        try {
            Thread.sleep(500);
        } catch (Exception e) {
        }
        state = XletManager.getState(context);
        if (state != 4 && state != 0) {
            System.out.println("Test failed, expecting 4 (destroyed) or 0 (unknown), received " + state);
            return;
        }
        context.notifyPaused();
        try {
            Thread.sleep(500);
        } catch (Exception e) {
        }
        state = XletManager.getState(context);
        if (state != 4 && state != 0) {
            System.out.println("Test failed, expecting 4 (destroyed) or 0 (unknown), received " + state);
            return;
        }
        context.resumeRequest();
        try {
            Thread.sleep(500);
        } catch (Exception e) {
        }
        state = XletManager.getState(context);
        if (state != 4 && state != 0) {
            System.out.println("Test failed, expecting 4 (destroyed) or 0 (unknown), received " + state);
            return;
        }
        System.out.println("Test passed");
    }
}
