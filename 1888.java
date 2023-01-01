import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class FileFrame extends JFrame implements ActionListener {

    /** variables for the file reading pop-up frame **/
    private Electro2D electro2D;

    private WindowListener ffwl;

    private int fileNum;

    private final String directoryString = "." + File.separator + ".." + File.separator + "data";

    private JTextArea instructions;

    private JLabel select;

    private JComboBox choice;

    private JButton button;

    private JPanel center;

    private JPanel south;

    private String[] sa;

    public FileFrame(Electro2D e, int i) {
        fileNum = i;
        electro2D = e;
        setTitle("Load Protein Data File");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        instructions = new JTextArea();
        instructions.append("Instructions: Select the name of the file that contains your protein sequence data.\n" + "Please note: Some files may take longer to load.");
        instructions.setEditable(false);
        instructions.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        select = new JLabel("Select Filename: ", JLabel.RIGHT);
        String[] files = { "file1", "file2", "file3", "file4" };
        choice = new JComboBox();
        for (String f : files) choice.addItem(f);
        button = new JButton("Load");
        button.addActionListener(this);
        getContentPane().add(instructions, BorderLayout.NORTH);
        center = new JPanel();
        center.add(select);
        center.add(choice);
        getContentPane().add(center, BorderLayout.CENTER);
        south = new JPanel();
        south.add(button);
        getContentPane().add(south, BorderLayout.SOUTH);
        pack();
        refreshFileList();
    }

    public void refreshFileList() {
        choice.removeAllItems();
        File fl = new File("data");
        if (!fl.exists()) {
            System.err.println("Warning: No data files found!");
            fl.mkdir();
        }
        sa = fl.list(new ImageFilter());
        for (int file = 0; file < sa.length; file++) {
            choice.addItem(sa[file]);
        }
    }

    public void actionPerformed(ActionEvent e) {
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        loadFile();
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        if (fileNum == 1) {
            electro2D.refreshProteinList();
        } else if (fileNum == 2) {
            electro2D.refreshProteinList2();
        }
        dispose();
        refreshFileList();
    }

    public void loadFile() {
        String filename = sa[choice.getSelectedIndex()];
        if (filename == null || filename.equals("")) {
            MessageFrame error = new MessageFrame();
            error.setMessage("Please enter a file name.");
            error.show();
        } else {
            String extension = filename.substring(filename.lastIndexOf(".") + 1);
            if (!extension.equalsIgnoreCase("faa") && !extension.equalsIgnoreCase("fasta") && !extension.equalsIgnoreCase("pdb") && !extension.equalsIgnoreCase("gbk") && !extension.equalsIgnoreCase("e2d")) {
                MessageFrame error = new MessageFrame();
                error.setMessage("File extension is not valid.");
                error.show();
            } else {
                if (extension.equalsIgnoreCase("faa") || extension.equalsIgnoreCase("fasta")) {
                    GenomeFileParser.fastaParse(filename, electro2D, "", fileNum);
                } else if (extension.equalsIgnoreCase("pdb")) {
                    GenomeFileParser.pdbParse(filename, electro2D, "", fileNum);
                } else if (extension.equalsIgnoreCase("gbk")) {
                    GenomeFileParser.gbkParse(filename, electro2D, "", fileNum);
                } else if (extension.equalsIgnoreCase("e2d")) {
                    GenomeFileParser.e2dParse(filename, electro2D, "", fileNum);
                }
                JOptionPane.showMessageDialog(null, "Proteins loaded.");
            }
        }
    }
}
