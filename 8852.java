import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.gjt.sp.jedit.*;

/**
 * This class creates an OptionPane for use with jEdit's "Plugins Options"
 * function.
 */
public class TemplatesOptionPane extends AbstractOptionPane implements ActionListener {

    protected JTextField dirTextField;

    protected TemplatesAction myAction;

    protected boolean pre_2_4_6 = false;

    public TemplatesOptionPane(TemplatesAction ta) {
        super("Templates");
        myAction = ta;
        try {
            Class superclass = Class.forName("org.gjt.sp.jedit.AbstractOptionPane");
            java.lang.reflect.Method dummy = superclass.getDeclaredMethod("_init", null);
        } catch (NoSuchMethodException nsme) {
            pre_2_4_6 = true;
            this._init();
        } catch (ClassNotFoundException cnfe) {
        }
    }

    public void _init() {
        this.init(jEdit.getProperty("plugin.TemplatesPlugin.templateDir.0", ""));
    }

    public void _save() {
        jEdit.setProperty("plugin.TemplatesPlugin.templateDir.0", dirTextField.getText());
        myAction.refreshTemplates();
    }

    /**
	 * Initialize the TemplatesOptionPane.
	 * @param textFieldStr A string containing the current Templates directory.
	 */
    public void init(String textFieldStr) {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(5, 5, 5, 5));
        JLabel chooseDirLabel = new JLabel(jEdit.getProperty("options.Templates.choose-dir-msg"));
        add(chooseDirLabel, BorderLayout.NORTH);
        JPanel p = new JPanel();
        BorderLayout bl = new BorderLayout();
        bl.setHgap(5);
        p.setLayout(bl);
        dirTextField = new JTextField(textFieldStr);
        p.add(dirTextField, BorderLayout.CENTER);
        JButton chooseBtn = new JButton(jEdit.getProperty("options.Templates.choose-btn-msg"));
        chooseBtn.addActionListener(this);
        p.add(chooseBtn, BorderLayout.EAST);
        JPanel p2 = new JPanel();
        p2.setLayout(new BorderLayout());
        p2.add(p, BorderLayout.NORTH);
        add(p2, BorderLayout.CENTER);
    }

    /**
	 * Save the new Templates directory to the global properties, and refresh 
	 * the Templates menu.
	 */
    public void save() {
        if (pre_2_4_6) {
            this._save();
        } else {
            super.save();
        }
    }

    /**
	 * Display the file chooser and respond to the user's selection.
	 * @param evt The ActionEvent corresponding to the user's button press.
	 */
    public void actionPerformed(ActionEvent evt) {
        JFileChooser chooser = new JFileChooser(dirTextField.getText());
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int retVal = chooser.showDialog(this, jEdit.getProperty("options.Templates.choose-btn-msg"));
        if (retVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            if (file != null) {
                try {
                    String dirName = file.getCanonicalPath();
                    dirTextField.setText(dirName);
                } catch (IOException e) {
                }
            }
        }
    }
}
