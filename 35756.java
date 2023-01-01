import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;

public class Thd extends Thread implements InterInterface {

    private int sle;

    private int exti = 0;

    private int suti = 0;

    public Thd(String name) {
        super(name);
        sle = 1000;
    }

    public void run() {
        try {
            exti = Integer.parseInt(tfield02.getText());
            for (int i = exti; i > 0; i--) {
                Thread.sleep(sle);
                tfield02.setText(Integer.toString(i - 1));
            }
            startex.setEnabled(false);
            result.setEnabled(true);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
