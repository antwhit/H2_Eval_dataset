import java.awt.*;
import javax.swing.*;

public class Tape extends Container {

    private TapeTextField left;

    private TapeTextField center;

    private TapeTextField right;

    private Machine machine;

    public Tape(Machine machine) {
        this.machine = machine;
        left = new TapeTextField(machine.slate, this);
        right = new TapeTextField(machine.slate, this);
        left.setHorizontalAlignment(JTextField.RIGHT);
        right.setHorizontalAlignment(JTextField.LEFT);
        setLayout(new GridLayout(1, 2));
        add(left);
        add(right);
    }

    public String getSymbol() {
        if (right.getText().length() == 0) return " "; else return right.getText().substring(0, 1);
    }

    public void go(String s, int m) {
        String l = left.getText();
        String r = right.getText();
        if (r.length() == 0) {
            r = " ";
        }
        r = s + r.substring(1);
        if (m > 0) {
            r = r + getSpaces(m - r.length());
            l = l + r.substring(0, m);
            r = r.substring(m);
        } else if (m < 0) {
            l = getSpaces(-m - l.length()) + l;
            r = l.substring(l.length() - 2 - m) + r;
            l = l.substring(0, l.length() - 2 - m);
        }
        right.setText(r);
        left.setText(l);
    }

    private String getSpaces(int n) {
        String s = "";
        int i = 0;
        for (i = 0; i < n; i++) {
            s = s + " ";
        }
        return s;
    }

    public void goRight(String s) {
        left.setText(left.getText() + s);
        if (right.getText().length() > 0) right.setText(right.getText().substring(1));
    }

    public void goLeft(String s) {
    }
}
