import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JTextFieldDemo extends JApplet implements ActionListener {

    JTextField jtf;

    public void init() {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {

                public void run() {
                    makeGUI();
                }
            });
        } catch (Exception exc) {
            System.out.println("Can't create because of " + exc);
        }
    }

    private void makeGUI() {
        setLayout(new FlowLayout());
        jtf = new JTextField(15);
        add(jtf);
        jtf.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        showStatus(jtf.getText());
    }
}
