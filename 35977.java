import org.gjt.sp.jedit.*;

/**
 * A jEdit EditBus message to alert all TemplateMenu objects that they need 
 * to be updated.
 */
public class TemplatesChanged extends EBMessage {

    public TemplatesChanged() {
        super(null);
    }
}
