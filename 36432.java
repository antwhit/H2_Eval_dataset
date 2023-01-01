import java.awt.Robot;
import java.awt.event.KeyEvent;

public class TabStopper implements Runnable {

    private boolean isWorking = false;

    private MenuFrame parent;

    public TabStopper(MenuFrame parent) {
        this.parent = parent;
        new Thread(this, "TabStopper").start();
    }

    public void run() {
        this.isWorking = true;
        Robot robot;
        try {
            robot = new Robot();
            while (isWorking) {
                robot.keyRelease(KeyEvent.VK_ALT);
                robot.keyRelease(KeyEvent.VK_TAB);
                parent.requestFocus();
                Thread.sleep(10);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        this.isWorking = false;
    }

    public boolean isWorking() {
        return this.isWorking;
    }
}
