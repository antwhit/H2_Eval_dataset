import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import fileexplorer.pref.*;

public class PrefPane extends JFrame {

    protected JButton okButton;

    protected JLabel prefsText;

    public PrefPane() {
        super();
        JTabbedPane topLevelMenu = new JTabbedPane(JTabbedPane.TOP);
        Location locationTab = new Location();
        View viewTab = new View();
        Colours colorTab = new Colours();
        topLevelMenu.add(locationTab);
        topLevelMenu.add(viewTab);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.getContentPane().add(topLevelMenu);
        setSize(390, 300);
        setLocation(20, 40);
    }
}
