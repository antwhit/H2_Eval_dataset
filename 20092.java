import javax.swing.JOptionPane;

public class Number15Swing {

    public static void main(String[] args) {
        int quarters;
        int dimes;
        int nickels;
        int pennies;
        int dollars;
        int totalCents;
        int cents;
        String strQuarters;
        String strDimes;
        String strNickels;
        String strPennies;
        strQuarters = JOptionPane.showInputDialog(null, "Enter the number of quarters");
        strDimes = JOptionPane.showInputDialog(null, "Enter the number of dimes");
        strNickels = JOptionPane.showInputDialog(null, "Enter the number of nickels.");
        strPennies = JOptionPane.showInputDialog(null, "Enter the number of pennies.");
        quarters = Integer.parseInt(strQuarters) * 25;
        dimes = Integer.parseInt(strDimes) * 10;
        nickels = Integer.parseInt(strNickels) * 5;
        pennies = Integer.parseInt(strPennies) * 1;
        totalCents = quarters + dimes + nickels + pennies;
        dollars = (totalCents / 100);
        cents = (totalCents % 100);
        System.out.println("Your change equalled " + dollars + " dollars and " + cents + " cents");
    }
}
