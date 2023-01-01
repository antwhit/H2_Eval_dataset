import javax.swing.Action;
import javax.swing.AbstractAction;
import java.awt.Frame;

public class ActionOpen extends JenuAction {

    public ActionOpen(Jenu owner) {
        super("Open...");
        m_owner = owner;
    }

    public void actionPerformed(java.awt.event.ActionEvent e) {
    }

    public void setEnabledFromState(int state) {
        setEnabled(enabledWhenStopped(state));
    }
}
