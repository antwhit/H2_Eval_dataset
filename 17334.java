import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class EditButton extends JButton implements ActionListener {

    protected InteractiveTableModel table;

    public EditButton(InteractiveTableModel t) {
        super("Edit");
        table = t;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        Verbs verbs = table.getVerbs();
        Dictionary dict = verbs.getDictionary();
        int idx = 0;
        VerbRecord vr = dict.getVerb(idx);
        EditFrame f = new EditFrame(verbs, vr, table);
        f.setVisible(true);
    }
}
