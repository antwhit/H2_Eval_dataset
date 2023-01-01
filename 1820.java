import javax.swing.JCheckBoxMenuItem;
import javax.swing.JInternalFrame;

@SuppressWarnings("serial")
class ChildMenuItem extends JCheckBoxMenuItem {

    private JInternalFrame frame;

    public ChildMenuItem(JInternalFrame frame) {
        super(frame.getTitle());
        this.frame = frame;
    }

    public JInternalFrame getFrame() {
        return frame;
    }
}
