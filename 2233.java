import javax.swing.JOptionPane;
import java.text.DecimalFormat;

public class Errors {

    public static void main(String[] args) {
        String line;
        int startMiles = 0;
        double endMiles = 0;
        double gallons = 0;
        boolean done = false;
        while (!done) {
            try {
                String start = JOptionPane.showInputDialog(null, "Enter starting mileage: ");
                startMiles = Integer.parseInt(start);
                String end = JOptionPane.showInputDialog(null, "Enter ending mileage: ");
                endMiles = Integer.parseInt(end);
                if (endMiles <= startMiles) throw new Exception();
                String amount = JOptionPane.showInputDialog(null, "Enter number of gallons: ");
                gallons = Integer.parseInt(amount);
                done = true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Your ending mileage," + endMiles + ", must be greater than your starting mileage, " + startMiles, "Error in Mileage", JOptionPane.ERROR_MESSAGE);
            }
        }
        JOptionPane.showMessageDialog(null, "Miles per gallon is " + (endMiles - startMiles) / gallons);
    }
}
