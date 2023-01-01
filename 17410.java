import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.text.*;
import javax.swing.*;
import javax.swing.event.*;

public class JxFrame extends JFrame {

    public JxFrame(String title) {
        super(title);
        setCloseClick();
        setLF();
    }

    private void setCloseClick() {
        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private void setLF() {
        String laf = UIManager.getSystemLookAndFeelClassName();
        try {
            UIManager.setLookAndFeel(laf);
        } catch (UnsupportedLookAndFeelException exc) {
            System.err.println("Warning: UnsupportedLookAndFeel: " + laf);
        } catch (Exception exc) {
            System.err.println("Error loading " + laf + ": " + exc);
        }
    }
}
