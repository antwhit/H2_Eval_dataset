import controller.UIController;
import javax.microedition.midlet.MIDletStateChangeException;
import javax.microedition.midlet.MIDlet;

/**
 * The Mini-Pauker-MIDlet
 *
 * @author Markus Brosch (markus[at]brosch[dot]net)
 */
public class MiniPaukerMIDlet extends MIDlet {

    private UIController mainController;

    public MiniPaukerMIDlet() {
        super();
    }

    protected void startApp() {
        System.out.println("MiniPaukerMIDlet.startApp called; first-time entry: " + (mainController == null));
        if (mainController == null) {
            mainController = new UIController(this);
        }
    }

    protected void pauseApp() {
        System.out.println("MiniPaukerMIDlet.pauseApp called");
    }

    protected void destroyApp(boolean b) throws MIDletStateChangeException {
        System.out.println("MiniPaukerMIDlet.destroyApp called: " + b);
        notifyDestroyed();
    }
}
