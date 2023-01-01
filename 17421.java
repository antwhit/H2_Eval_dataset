import monitor.io.Controller;
import monitor.io.ControllerServerLink;
import monitor.io.Event;
import monitor.modelo.Speaker;
import monitor.server.Tools;

public class HangupController implements Controller {

    private final String eventName = "hangup";

    private long modificationDate;

    private ControllerServerLink csl;

    public HangupController(ControllerServerLink csl) {
        this.csl = csl;
    }

    /**
     * Returns the controlled event name
     * @return The controlled event name
     */
    public String getControlledEvent() {
        return eventName;
    }

    /**
     * Returns the priority level of this controller against other controllers of the same type
     * Best priority is near negative infinity, worst is near positive infinity.
     * Usually between 1 and 10
     * @return The priority level of this controller
     */
    public int getPriority() {
        return 10;
    }

    /**
     * This is the main method in the class, which manages the event action
     * @param event The event received from asterisk that is about to be treated
     * @throws Exception If an Exception occurs, like channel is not Zap, or terminal doesn't exist.
     */
    public void process(Event event) throws Exception {
        String channel = event.get("channel");
        if (Tools.containsZap(channel)) {
            Speaker speaker = csl.getPBX().getSpeaker(channel);
            if (speaker != null) {
                csl.generateActionEvent(Tools.EVENT_CALL_TERMINATED, new Object[] { speaker });
                csl.log("Call on channel " + channel + " recorded as result " + speaker.getResultado());
            }
        }
    }

    public long getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(long date) {
        this.modificationDate = date;
    }
}
