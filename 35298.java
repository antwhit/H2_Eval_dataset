import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Language extends JFrame {

    private static final long serialVersionUID = 5L;

    private static final String versionID = "1.2";

    private static final String versionDate = "February 21th 2010";

    private static Language language;

    private Settings settings;

    private WordWorker wordWorker;

    private Language() {
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItem;
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        menuItem = new JMenuItem("Quit", KeyEvent.VK_Q);
        menu.add(menuItem);
        menuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                closingWindow();
                dispose();
            }
        });
        menuBar.add(menu);
        menu = new JMenu("About");
        menu.setMnemonic(KeyEvent.VK_A);
        menuItem = new JMenuItem("Information");
        menuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                showDialog("<html>You are currently running version " + versionID + "<br>" + versionDate + "<br>" + "by Jean Christophe Beyler<br>" + "fearyourself@hotmail.com");
            }
        });
        menu.add(menuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
        settings = new Settings();
        JTabbedPane tabbedPane = new JTabbedPane();
        ImageIcon icon = null;
        wordWorker = new WordWorker();
        tabbedPane.addTab("Work", icon, wordWorker, "Work on your vocabulary");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        Statistics stats = new Statistics();
        tabbedPane.addTab("Statistics", icon, stats, "Statistics");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        Table panel2 = new Table();
        tabbedPane.addTab("Word list", icon, panel2, "The word list");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
        tabbedPane.addTab("Settings", icon, settings, "Settings");
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
        add(tabbedPane);
        setSize(500, 600);
        setTitle("Vocabulary learner");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation((size.width - getWidth()) / 4, (size.height - getHeight()) / 2);
        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                closingWindow();
            }
        });
        setVisible(true);
    }

    public void checkSettings() {
        try {
            FileInputStream fstream = new FileInputStream("settings.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine = br.readLine();
            if (strLine != null) {
                settings.readDataFile(strLine);
            }
            wordWorker.getNewWords(br);
            in.close();
            wordWorker.setShouldUpdate(true);
            Dictionary.getDictionary().displayLevel();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            wordWorker.setShouldUpdate(true);
        }
    }

    public static Language getLanguage() {
        if (language == null) language = new Language();
        return language;
    }

    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }

    public void closingWindow() {
        System.out.println("Window closing");
        settings.writeOut();
        wordWorker.resetNewWords();
        wordWorker.writeOut();
    }

    public void showDialog(String s) {
        JOptionPane.showMessageDialog(this, s);
    }

    public static void main(String[] args) {
        Language simple = Language.getLanguage();
        simple.checkSettings();
    }

    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
