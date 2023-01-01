import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TaxPayer extends JFrame implements ActionListener {

    DataInputStream input;

    JPanel[] boxPanel = new JPanel[10];

    JPanel fieldPanel = new JPanel();

    JPanel buttonPanel = new JPanel();

    JLabel[] boxLabel = new JLabel[10];

    JTextField[] box = new JTextField[10];

    JButton nextButton = new JButton("Next");

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "The UIManager could not set the Look and Feel for this application.", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        TaxPayer f = new TaxPayer();
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.setSize(450, 300);
        f.setTitle("TaxPayer");
        f.setResizable(false);
        f.setLocation(200, 200);
        f.setVisible(true);
    }

    public TaxPayer() {
        Container c = getContentPane();
        c.setLayout((new BorderLayout()));
        fieldPanel.setLayout(new GridLayout(5, 2));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        for (int count = 0; count < 10; count++) {
            boxLabel[count] = new JLabel();
        }
        boxLabel[0].setText("1 Wages, tips, other comp.");
        boxLabel[1].setText("2 Federal income tax withheld");
        boxLabel[2].setText("3 Social security wages");
        boxLabel[3].setText("4 Social security tax withheld");
        boxLabel[4].setText("5 Medicare wages and tips");
        boxLabel[5].setText("6 Medicare tax withheld");
        boxLabel[6].setText("7 Social security tips");
        boxLabel[7].setText("8 Allocated tips");
        boxLabel[8].setText("9 Advance EIC payment");
        boxLabel[9].setText("10 Dependent care benefits");
        for (int count = 0; count < 10; count++) {
            box[count] = new JTextField(10);
        }
        for (int count = 0; count < 10; count++) {
            boxPanel[count] = new JPanel(new BorderLayout());
            boxPanel[count].add(boxLabel[count], BorderLayout.NORTH);
            boxPanel[count].add(box[count], BorderLayout.SOUTH);
        }
        for (int count = 0; count < 10; count++) {
            fieldPanel.add(boxPanel[count]);
        }
        buttonPanel.add(nextButton);
        c.add(fieldPanel, BorderLayout.CENTER);
        c.add(buttonPanel, BorderLayout.SOUTH);
        nextButton.addActionListener(this);
        try {
            input = new DataInputStream(new FileInputStream("W2s.dat"));
        } catch (IOException io) {
            JOptionPane.showMessageDialog(null, "The program could not create a storage location. Please check the disk drive and then run the program again.", "Error", JOptionPane.INFORMATION_MESSAGE);
            System.exit(1);
        }
        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit and submit the file?", "File Submission", JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.YES_OPTION) System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        String arg = e.getActionCommand();
        try {
            for (int count = 0; count < 10; count++) {
                box[count].setText(input.readUTF());
            }
            JOptionPane.showMessageDialog(null, "The payment information has been saved.", "Submission Successful", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException c) {
            System.exit(1);
        }
        clearFields();
    }

    public void clearFields() {
        for (int count = 0; count < 10; count++) {
            box[count].setText("");
        }
    }
}
