import java.awt.*;
import javax.swing.*;
import org.gjt.sp.jedit.*;

public class MacOSOptionPane extends AbstractOptionPane {

    private JCheckBox menuBox;

    private JCheckBox preserveBox;

    private JCheckBox liveResizeBox;

    public MacOSOptionPane() {
        super("MacOSPlugin");
    }

    public void _init() {
        Dimension d = new Dimension(7, 7);
        menuBox = new JCheckBox(jEdit.getProperty("options.MacOSPlugin.menubar.label"));
        addComponent(menuBox);
        addComponent(new JLabel("(Requires restart for changes to take effect)"));
        addComponent(new Box.Filler(d, d, d));
        preserveBox = new JCheckBox(jEdit.getProperty("options.MacOSPlugin.preserve.label"));
        addComponent(preserveBox);
        addComponent(new Box.Filler(d, d, d));
        liveResizeBox = new JCheckBox(jEdit.getProperty("options.MacOSPlugin.liveResize.label"));
        addComponent(liveResizeBox);
        addComponent(new JLabel("(Requires restart for changes to take effect)"));
        getSettings();
    }

    public void _save() {
        jEdit.setBooleanProperty("MacOSPlugin.useScreenMenuBar", menuBox.isSelected());
        jEdit.setBooleanProperty("MacOSPlugin.preserveCodes", preserveBox.isSelected());
        jEdit.setBooleanProperty("MacOSPlugin.liveResize", liveResizeBox.isSelected());
    }

    public void getSettings() {
        menuBox.setSelected(jEdit.getBooleanProperty("MacOSPlugin.useScreenMenuBar", jEdit.getBooleanProperty("MacOSPlugin.default.useScreenMenuBar")));
        preserveBox.setSelected(jEdit.getBooleanProperty("MacOSPlugin.preserveCodes", jEdit.getBooleanProperty("MacOSPlugin.default.preserveCodes")));
        liveResizeBox.setSelected(jEdit.getBooleanProperty("MacOSPlugin.liveResize", jEdit.getBooleanProperty("MacOSPlugin.default.liveResize")));
    }
}
