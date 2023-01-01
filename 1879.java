import javax.microedition.midlet.*;
import com.sun.midp.security.*;
import com.sun.midp.events.*;

/**
 * III (Integrated Internal Interface) testing stand-alone MIDlet.
 */
public class PauseTest extends MIDlet {

    boolean firstTime = true;

    boolean suspended = false;

    /**
     * Inner class to request security token from SecurityInitializer.
     * SecurityInitializer should be able to check this inner class name.
     */
    private static class SecurityTrusted implements ImplicitlyTrustedClass {
    }

    ;

    /** Security token to allow access to implementation APIs */
    private static SecurityToken myToken = SecurityInitializer.requestToken(new SecurityTrusted());

    private EventQueue myQ;

    public PauseTest() {
        myQ = EventQueue.getEventQueue(myToken);
    }

    public void startApp() {
        if (firstTime) {
            firstTime = false;
        }
        UnitTestEvent suspendEvent = new UnitTestEvent(EventTypes.SYSTEM_EVENT);
        suspendEvent.intParam1 = (EventTypes.SUSPEND_ALL);
        if (!suspended) {
            System.out.println("startApp() called on startup");
            myQ.post(suspendEvent);
        } else {
            System.out.println("PASSED: startApp() called on resume");
            notifyDestroyed();
        }
    }

    public void pauseApp() {
        suspended = true;
        System.out.println("pauseApp() called");
        UnitTestEvent resumeEvent = new UnitTestEvent(EventTypes.SYSTEM_EVENT);
        resumeEvent.intParam1 = (EventTypes.RESUME_ALL);
        myQ.post(resumeEvent);
    }

    public void destroyApp(boolean unconditional) {
        System.out.println("destroyApp() called");
    }
}
