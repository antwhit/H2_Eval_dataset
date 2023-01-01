import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MEFrame extends JFrame implements ResourceActivationListener, ActionListener {

    protected JSplitPane jsp;

    protected ResourceList rlist;

    protected JScrollPane resScrollPane;

    protected MetadataEditorPanel mep;

    protected JLabel statusBar;

    protected OptionDialog optionDialog;

    protected String lang = Metadata.DEFAULT_LANG;

    protected Action quitAction;

    public class SetupAction extends AbstractAction {

        MEFrame m;

        public SetupAction(MEFrame m) {
            super("Setup");
            this.m = m;
        }

        public void actionPerformed(ActionEvent e) {
            m.setup();
        }
    }

    public class QuitAction extends AbstractAction {

        MEFrame m;

        public QuitAction(MEFrame m) {
            super("Esci");
            this.m = m;
        }

        public void actionPerformed(ActionEvent e) {
            m.quit();
        }
    }

    protected JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menuBar.add(menu);
        JMenuItem menuItem = new JMenuItem(new SetupAction(this));
        menu.add(menuItem);
        menuItem = new JMenuItem(quitAction = new QuitAction(this));
        menu.add(menuItem);
        menu = new JMenu("Lingua");
        menuBar.add(menu);
        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem rbMenuItem = new JRadioButtonMenuItem("Italiano");
        rbMenuItem.setActionCommand("it");
        rbMenuItem.setSelected(true);
        rbMenuItem.addActionListener(this);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);
        rbMenuItem = new JRadioButtonMenuItem("Spagnolo");
        rbMenuItem.setActionCommand("es");
        rbMenuItem.addActionListener(this);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);
        rbMenuItem = new JRadioButtonMenuItem("Inglese");
        rbMenuItem.setActionCommand("en");
        rbMenuItem.addActionListener(this);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);
        rbMenuItem = new JRadioButtonMenuItem("Francese");
        rbMenuItem.setActionCommand("fr");
        rbMenuItem.addActionListener(this);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);
        rbMenuItem = new JRadioButtonMenuItem("Tedesco");
        rbMenuItem.setActionCommand("de");
        rbMenuItem.addActionListener(this);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);
        return menuBar;
    }

    public MEFrame() {
        super("LaComune metadata editor");
        setJMenuBar(createMenuBar());
        getContentPane().add(jsp = new JSplitPane());
        getContentPane().add(statusBar = new JLabel("Lingua: " + lang), BorderLayout.SOUTH);
        resScrollPane = new JScrollPane(rlist = new ResourceList());
        resScrollPane.setPreferredSize(new Dimension(100, 200));
        jsp.setLeftComponent(resScrollPane);
        jsp.setRightComponent(mep = new MetadataEditorPanel());
        rlist.addResourceActivationListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        setLanguage(e.getActionCommand());
    }

    public void setLanguage(String lang) {
        this.lang = lang;
        statusBar.setText("Lingua: " + lang);
        mep.setLanguage(lang);
    }

    public void resourceActivated(Resource r) {
        mep.setResource(r);
    }

    public void addResource(Resource r) {
        rlist.addResource(r);
    }

    public void setup() {
        if (optionDialog == null) optionDialog = new OptionDialog(this, "Setup");
        optionDialog.setLocationRelativeTo(this);
        optionDialog.setVisible(true);
    }

    public void quit() {
        mep.save();
        System.exit(0);
    }
}
