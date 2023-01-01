import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.net.*;
import java.util.Vector;
import java.util.StringTokenizer;
import org.gjt.sp.jedit.browser.VFSBrowser;
import org.gjt.sp.jedit.gui.*;
import org.gjt.sp.jedit.*;
import org.gjt.sp.util.Log;

public class TaskListGeneralOptionPane extends AbstractOptionPane {

    public TaskListGeneralOptionPane() {
        super("tasklist.general");
    }

    protected void _init() {
        String _parseDelay = "";
        try {
            _parseDelay = "" + Integer.parseInt(jEdit.getProperty("tasklist.parsedelay"));
        } catch (NumberFormatException nf) {
            _parseDelay = "1000";
        }
        addComponent(jEdit.getProperty("options.tasklist.general.parsedelay"), parseDelay = new JTextField(_parseDelay));
        addComponent(Box.createVerticalStrut(3));
        addComponent(jEdit.getProperty("options.tasklist.general.buffer.display"), bufferDisplay = new JComboBox(new String[] { jEdit.getProperty("options.tasklist.general.buffer.display.fullpath"), jEdit.getProperty("options.tasklist.general.buffer.display.namedir"), jEdit.getProperty("options.tasklist.general.buffer.display.nameonly") }));
        addComponent(Box.createVerticalStrut(3));
        addComponent(highlightTasks = new JCheckBox(jEdit.getProperty("options.tasklist.general.highlight.tasks"), jEdit.getBooleanProperty("tasklist.highlight.tasks", true)));
        addComponent(Box.createVerticalStrut(3));
        addComponent(jEdit.getProperty("options.tasklist.general.highlight.color"), highlightColor = createColorButton("tasklist.highlight.color"));
        highlightTasks.addActionListener(new HighlightColorHandler());
        highlightColor.setEnabled(highlightTasks.isSelected());
        String _bufferDisplay = jEdit.getProperty("tasklist.buffer.display");
        if (_bufferDisplay == "" || _bufferDisplay == null) bufferDisplay.setSelectedItem(jEdit.getProperty("options.tasklist.general.buffer.display.namedir")); else bufferDisplay.setSelectedItem(_bufferDisplay);
    }

    public void _save() {
        jEdit.setProperty("tasklist.parsedelay", parseDelay.getText());
        jEdit.setProperty("tasklist.buffer.display", bufferDisplay.getSelectedItem().toString());
        jEdit.setProperty("tasklist.highlight.color", GUIUtilities.getColorHexString(highlightColor.getBackground()));
        jEdit.setBooleanProperty("tasklist.highlight.tasks", highlightTasks.isSelected());
    }

    private JButton createColorButton(String property) {
        JButton b = new JButton(" ");
        b.setBackground(GUIUtilities.parseColor(jEdit.getProperty(property)));
        b.addActionListener(new ColorButtonHandler(b));
        b.setRequestFocusEnabled(false);
        return b;
    }

    private class ColorButtonHandler implements ActionListener {

        private JButton button;

        public ColorButtonHandler(JButton button) {
            this.button = button;
        }

        public void actionPerformed(ActionEvent evt) {
            JButton button = (JButton) evt.getSource();
            Color c = JColorChooser.showDialog(TaskListGeneralOptionPane.this, jEdit.getProperty("colorChooser.title"), button.getBackground());
            if (c != null) {
                button.setBackground(c);
            }
        }
    }

    private class HighlightColorHandler implements ActionListener {

        public void actionPerformed(ActionEvent evt) {
            TaskListGeneralOptionPane.this.highlightColor.setEnabled(TaskListGeneralOptionPane.this.highlightTasks.isSelected());
        }
    }

    private JTextField parseDelay;

    private JComboBox bufferDisplay;

    private JCheckBox highlightTasks;

    private JButton highlightColor;
}
