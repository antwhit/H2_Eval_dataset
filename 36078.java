import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class AddButton extends JButton implements ActionListener {

    protected Verbs verbs;

    protected InteractiveTableModel table;

    public AddButton(Verbs v, InteractiveTableModel t) {
        super("Ajouter");
        verbs = v;
        table = t;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        VerbRecord vr = verbs.getDictionary().findVerb("New verb");
        if (vr == null) {
            vr = new VerbRecord(1, "New verb", "Nouveau verbe");
            verbs.getDictionary().addVerb(vr);
        } else verbs.showDialog("Cannot create new verb, it already exists in list. Change its name to create after a new verb");
        EditFrame f = new EditFrame(verbs, vr, table);
        f.setVisible(true);
    }
}
