import java.awt.*;
import java.awt.event.*;

public class Telephone extends Frame implements ActionListener {

    private Button keysArray[];

    private Panel keyPad;

    private TextField lcdField;

    private Label fieldLabel;

    private boolean foundKey;

    private boolean clearText;

    public Telephone() {
        lcdField = new TextField(20);
        keyPad = new Panel();
        fieldLabel = new Label("Click each button above to dial your number.");
        keysArray = new Button[12];
        clearText = true;
        for (int i = 1; i <= 10; i++) keysArray[i] = new Button(String.valueOf(i));
        keysArray[10] = new Button("*");
        keysArray[0] = new Button("0");
        keysArray[11] = new Button("#");
        lcdField.setEditable(false);
        setBackground(Color.magenta);
        setLayout(new BorderLayout());
        keyPad.setLayout(new GridLayout(4, 3, 10, 10));
        for (int i = 1; i <= 3; i++) keyPad.add(keysArray[i]);
        for (int i = 4; i <= 6; i++) keyPad.add(keysArray[i]);
        for (int i = 7; i <= 9; i++) keyPad.add(keysArray[i]);
        for (int i = 0; i < keysArray.length; i++) keysArray[i].addActionListener(this);
        keyPad.add(keysArray[10]);
        keyPad.add(keysArray[0]);
        keyPad.add(keysArray[11]);
        add(lcdField, BorderLayout.NORTH);
        add(keyPad, BorderLayout.CENTER);
        add(fieldLabel, BorderLayout.SOUTH);
        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        foundKey = false;
        for (int i = 0; i < keysArray.length && !foundKey; i++) if (e.getSource() == keysArray[i]) {
            foundKey = true;
            switch(i) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                    if (clearText) {
                        lcdField.setText("");
                        clearText = false;
                    }
                    lcdField.setText(lcdField.getText() + keysArray[i].getLabel());
                    break;
            }
        }
    }

    public static void main(String args[]) {
        Telephone phoneFrame = new Telephone();
        phoneFrame.setBounds(50, 130, 250, 300);
        phoneFrame.setTitle("Telephone");
        phoneFrame.setVisible(true);
    }
}
