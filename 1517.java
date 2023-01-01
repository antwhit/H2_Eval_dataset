import javax.microedition.lcdui.List;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;

public class Lista extends List implements CommandListener {

    protected Main parent;

    public Lista(Main _parent, String nazwa) {
        super(nazwa, List.IMPLICIT);
        parent = _parent;
        try {
            setCommandListener(parent);
        } catch (Exception e) {
            append(e.getMessage(), null);
        }
    }

    /** Czy�ci list� */
    public void clear() {
        while (size() > 0) delete(0);
    }

    /**
     * Called when action should be handled
     */
    public void commandAction(Command command, Displayable displayable) {
    }
}
