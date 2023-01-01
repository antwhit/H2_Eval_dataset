import java.io.*;
import javax.swing.JOptionPane;

public class MyType {

    public static void main(String[] args) {
        String strChoice, strTryString, strTryInt, strTryDouble;
        int choice, tryInt;
        double tryDouble;
        boolean done = false;
        while (!done) {
            try {
                String message = "What's My Type?\n\n1) String\n2) integer\n3) double\n4) Quit the program";
                strChoice = JOptionPane.showInputDialog(null, message);
                if (strChoice == null) choice = 4; else choice = Integer.parseInt(strChoice);
                switch(choice) {
                    case 1:
                        strTryString = JOptionPane.showInputDialog(null, "Please enter a string data type.");
                        JOptionPane.showMessageDialog(null, "The data type you entered is correct.");
                        break;
                    case 2:
                        strTryInt = JOptionPane.showInputDialog(null, "Please enter an integer data type.");
                        tryInt = Integer.parseInt(strTryInt);
                        JOptionPane.showMessageDialog(null, "The data type you entered is correct.");
                        break;
                    case 3:
                        strTryDouble = JOptionPane.showInputDialog(null, "Please enter a double data type.");
                        tryDouble = Double.parseDouble(strTryDouble);
                        JOptionPane.showMessageDialog(null, "The data type you entered is correct.");
                        break;
                    case 4:
                        done = true;
                        JOptionPane.showMessageDialog(null, "Thank you for using the data type validation program.");
                        break;
                    default:
                        throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "You entered an incorrect data type.\nPlease try again.");
            }
        }
    }
}
