import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class New implements ActionListener {

    int count;

    String A[] = new String[100];

    String inVal;

    private static final int WINDOW_WIDTH = 300;

    private static final int WINDOW_HEIGHT = 300;

    private static final int TEXT_WIDTH = 23;

    private static final String LEGEND = "Enter information of New Contact";

    private static final FlowLayout LAYOUT_STYLE = new FlowLayout();

    private JFrame window = new JFrame("Phonebook");

    private JTextArea legendArea = new JTextArea(LEGEND, 2, TEXT_WIDTH);

    public JLabel nameTag = new JLabel("Name");

    public JTextField nameText = new JTextField(TEXT_WIDTH);

    public JLabel numberTag = new JLabel("Number");

    public JTextField numberText = new JTextField(TEXT_WIDTH);

    private JButton enterButton = new JButton("Enter");

    private FileOutputStream out;

    private PrintStream p;

    New() {
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        legendArea.setEditable(false);
        legendArea.setLineWrap(true);
        legendArea.setWrapStyleWord(true);
        legendArea.setBackground(window.getBackground());
        nameText.setEditable(true);
        numberText.setEditable(true);
        enterButton.addActionListener(this);
        window.setLayout(LAYOUT_STYLE);
        window.setTitle("New Contact");
        window.add(legendArea);
        window.add(nameTag);
        window.add(nameText);
        window.add(numberTag);
        window.add(numberText);
        window.add(enterButton);
        window.setVisible(true);
        nameTag.setVisible(true);
        nameText.setVisible(true);
        numberTag.setVisible(true);
        numberText.setVisible(true);
        enterButton.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enterButton) {
            try {
                File F = new File("myfile.txt");
                Scanner fileIn = new Scanner(F);
                count = 0;
                while (fileIn.hasNextLine()) {
                    inVal = fileIn.nextLine();
                    A[count] = inVal;
                    count++;
                }
            } catch (FileNotFoundException b) {
                System.err.println("Error writing to file");
            }
            A[count] = nameText.getText();
            count++;
            A[count] = numberText.getText();
            try {
                out = new FileOutputStream("myfile.txt");
                p = new PrintStream(out);
                for (int i = 0; i <= count; i++) {
                    p.println(A[i]);
                }
                p.close();
            } catch (Exception a) {
                System.err.println("Error writing to file");
            }
            window.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new New();
    }
}
