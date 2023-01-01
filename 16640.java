import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;

public class FileChooserDemo extends JPanel implements ActionListener {

    private static final String newline = "\n";

    JButton openButton, saveButton;

    JTextArea log;

    JFileChooser fc;

    public FileChooserDemo() {
        super(new BorderLayout());
        log = new JTextArea(5, 20);
        log.setMargin(new Insets(5, 5, 5, 5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);
        FileFilter filter1 = new ExtensionFileFilter("Text Documents (*.txt)", "txt");
        fc = new JFileChooser();
        fc.setFileFilter(filter1);
        openButton = new JButton("Open a File...", createImageIcon("images/Open16.gif"));
        openButton.addActionListener(this);
        saveButton = new JButton("Save a File...", createImageIcon("images/Save16.gif"));
        saveButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openButton) {
            int returnVal = fc.showOpenDialog(FileChooserDemo.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                fc.showOpenDialog(FileChooserDemo.this);
                File file = fc.getSelectedFile();
                log.append("Opening: " + file.getPath() + "  " + file.getName() + "." + newline);
            } else {
                log.append("Open command cancelled by user." + newline);
            }
            JOptionPane.showMessageDialog(null, "Hello");
            log.setCaretPosition(log.getDocument().getLength());
        } else if (e.getSource() == saveButton) {
            int returnVal = fc.showSaveDialog(FileChooserDemo.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                JOptionPane.showMessageDialog(null, file.getPath() + "  " + file.getName());
                log.append("Saving: " + file.getPath() + "  " + file.getName() + "." + newline);
            } else {
                log.append("Save command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
        }
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = FileChooserDemo.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("FileChooserDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new FileChooserDemo());
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
    }
}
