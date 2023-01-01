import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.*;
import java.io.*;
import java.net.*;

public class AddResourceFileDialog extends JDialog implements ActionListener {

    JFileChooser filechooser;

    String application;

    ResourceFilesDialog dialog;

    public AddResourceFileDialog(ResourceFilesDialog dialog, String application) {
        super(dialog, true);
        filechooser = new JFileChooser();
        filechooser.addActionListener(this);
        this.application = application;
        this.dialog = dialog;
        this.setTitle("Add Resource File");
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(filechooser);
        setBounds(10, 10, 600, 600);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            File f = filechooser.getSelectedFile();
            FileInputStream fis = new FileInputStream(f);
            BufferedInputStream bis = new BufferedInputStream(fis);
            int available = bis.available();
            byte[] b = new byte[available];
            int read = bis.read(b, 0, available);
            bis.close();
            fis.close();
            dialog.AddResourceFile(f.getName(), b);
        } catch (Exception exc) {
        }
        this.setVisible(false);
        this.dispose();
    }
}
