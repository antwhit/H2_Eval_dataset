import java.awt.Color;
import javax.swing.JFrame;

public class RunCalc {

    public static void main(String[] args) {
        Calculadora aWindow = new Calculadora();
        aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aWindow.setLocationRelativeTo(null);
        aWindow.pack();
        aWindow.setResizable(false);
        aWindow.setBackground(Color.BLACK);
        aWindow.setVisible(true);
    }
}
