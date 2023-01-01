/**
 * Frame event state object containing information about the event.
 * @author Aki Tossavainen <cmouse@projectb2.net>
 * @version 1.0
 */
public class FrameEvent extends java.util.EventObject {

    private String event;

    private String[] argv;

    public FrameEvent(Object source, String event, String[] argv) {
        super(source);
        this.event = event;
        this.argv = argv;
    }

    /**
     * Returns the event string for this event
     * @return occured event
     */
    public String getEvent() {
        return event;
    }

    /**
     * Returns the arguments for this event
     * @return argument vector
     */
    public String[] getArgv() {
        return argv;
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append(source.toString());
        buf.append(" ");
        buf.append(event);
        if (argv != null) {
            buf.append(" ");
            for (int i = 0; i < argv.length; i++) {
                buf.append(argv[i]);
                if (i + 1 < argv.length) buf.append(" ");
            }
        }
        return buf.toString();
    }
}
