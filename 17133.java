import javax.swing.*;
import java.util.ArrayList;

public class SenezReader {

    private JTextField sysIn;

    private SenezConsole sc;

    public SenezReader(SenezConsole s, JTextField in) {
        sysIn = in;
        sc = s;
    }

    public char readChar() {
        sc.waiting = true;
        while (sc.waiting) {
        }
        String s = retrieve();
        if (s.length() == 0) {
            return '\0';
        }
        return s.charAt(0);
    }

    public int readInt() {
        sc.waiting = true;
        while (sc.waiting) {
        }
        String s = retrieve();
        return Integer.parseInt(s);
    }

    public double readDouble() {
        sc.waiting = true;
        while (sc.waiting) {
        }
        String s = retrieve();
        return Double.parseDouble(s);
    }

    public String readWord() {
        return read(" ");
    }

    public String readLine() {
        sc.waiting = true;
        while (sc.waiting) {
        }
        return retrieve();
    }

    public String read(String delim) {
        sc.waiting = true;
        while (sc.waiting) {
        }
        String s = retrieve();
        if (s.indexOf(delim) == -1) {
            return s;
        }
        return s.substring(0, s.indexOf(delim));
    }

    public String retrieve() {
        String s = sysIn.getText();
        sysIn.setText("");
        sc.println(s);
        return s;
    }
}
