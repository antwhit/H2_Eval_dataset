import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JTable;

public class CanonicaliseFilenamesAction extends AbstractAction {

    public CanonicaliseFilenamesAction() {
    }

    public CanonicaliseFilenamesAction(String name) {
        super(name);
    }

    public CanonicaliseFilenamesAction(String name, Icon icon) {
        super(name, icon);
    }

    public CanonicaliseFilenamesAction(String name, Icon icon, JTable table) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
