import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * This MIDlet will attempt to connect to an NXT brick and allow
 * the user to control the robot through a mobile device.
 */
public class NXTRemote extends MIDlet {

    private Display display;

    private NXTLocator locator;

    protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
    }

    protected void pauseApp() {
    }

    protected void startApp() throws MIDletStateChangeException {
        display = Display.getDisplay(this);
        locator = new NXTLocator(display);
        locator.findNXT();
    }
}
