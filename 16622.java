import javax.swing.JOptionPane;
import java.text.DecimalFormat;

public class Traffic {

    public static void main(String[] args) {
        String line;
        int speedLimit = 0;
        int offenderSpeed = 0;
        int tickets = 0;
        boolean done = false;
        while (!done) {
            try {
                String start = JOptionPane.showInputDialog(null, "Enter posted speed limit: ");
                speedLimit = Integer.parseInt(start);
                String end = JOptionPane.showInputDialog(null, "Enter offender speed: ");
                offenderSpeed = Integer.parseInt(end);
                if (offenderSpeed <= speedLimit) throw new Exception();
                String amount = JOptionPane.showInputDialog(null, "Enter number of previous tickets: ");
                tickets = Integer.parseInt(amount);
                done = true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        DecimalFormat twoDigits = new DecimalFormat("$#,###.00");
        String message = "The driver was " + (offenderSpeed - speedLimit) + " over the speed limit.";
        String message2 = "\n\nThe cost of the speeding ticket is " + 20 * (offenderSpeed - speedLimit);
        String message3 = "\n\nThe total cost is " + twoDigits.format(calculate(speedLimit, offenderSpeed, tickets));
        JOptionPane.showMessageDialog(null, message + message2 + message3);
        System.exit(0);
    }

    public static double calculate(int sl, int os, int t) {
        switch(t) {
            case 1:
                return (os - sl) * 20 + 74.80;
            case 2:
                return (os - sl) * 20 + 99.80;
            default:
                return (os - sl) * 20 + 124.80;
        }
    }
}
