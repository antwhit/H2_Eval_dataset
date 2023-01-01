import gui.GUIHandler;
import gui.GUIScreens;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import utils.ResourcesHandler;

/**
 * 
 * @author Daniel
 * Clase de entrada de la aplicaci�n.
 */
public class Practica10 extends MIDlet {

    public Practica10() {
    }

    protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
    }

    protected void pauseApp() {
    }

    protected void startApp() throws MIDletStateChangeException {
        try {
            ResourcesHandler.getInstance().init();
            GUIHandler.getInstance().init(this);
            GUIHandler.getInstance().showScreen(GUIScreens.STARTSCREEN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            destroyApp(true);
            notifyDestroyed();
        } catch (MIDletStateChangeException e) {
        }
    }
}
