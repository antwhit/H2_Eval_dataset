import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import org.gjt.sp.jedit.gui.HistoryTextField;
import org.gjt.sp.jedit.*;

public class ConsoleToolBar extends JToolBar {

    public ConsoleToolBar(View view) {
        setLayout(new BorderLayout());
        setFloatable(false);
        this.view = view;
        add(BorderLayout.WEST, shells = new JComboBox(EditBus.getNamedList(Shell.SHELLS_LIST)));
        shells.setSelectedItem(jEdit.getProperty("console.shell"));
        Box box = new Box(BoxLayout.Y_AXIS);
        box.add(Box.createGlue());
        cmd = new HistoryTextField("console");
        Dimension dim = cmd.getPreferredSize();
        dim.width = Integer.MAX_VALUE;
        cmd.setMaximumSize(dim);
        box.add(cmd);
        box.add(Box.createGlue());
        add(BorderLayout.CENTER, box);
        cmd.addActionListener(new ActionHandler());
    }

    private View view;

    private JComboBox shells;

    private HistoryTextField cmd;

    class ActionHandler implements ActionListener {

        public void actionPerformed(ActionEvent evt) {
            String command = cmd.getText();
            if (command != null && command.length() != 0) {
                cmd.addCurrentToHistory();
                cmd.setText(null);
                ConsoleFrame cons = ConsoleFramePluginPart.getConsole(view);
                cons.run((String) shells.getSelectedItem(), command);
            } else {
                ConsoleFrame cons = ConsoleFramePluginPart.getConsole(view);
                cons.selectShell((String) shells.getSelectedItem());
            }
        }
    }
}
