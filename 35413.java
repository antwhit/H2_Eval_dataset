import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SquareToneGenerator extends IO implements Component, ActionListener {

    private JInternalFrame f;

    private String name = "Square Tone Generator";

    private String[] pin_names = { "Out" };

    private int period_time = 1000;

    private int half_period = 500;

    private int frek = 1000;

    private JTextField period;

    private JLabel frekvens;

    private JLabel label1;

    private JLabel label2;

    private JButton set;

    private JButton Show_button;

    private Container content;

    private Project parent;

    private boolean running = true;

    public void init(Project parent, String data) {
        this.parent = parent;
        setWidth(1);
        setDirection(0x00);
        f = new JInternalFrame(name);
        f.setSize(250, 100);
        f.setLocation(30, 500);
        f.setVisible(true);
        content = f.getContentPane();
        content.setBackground(Color.white);
        content.setLayout(new FlowLayout());
        period = new JTextField(10);
        frekvens = new JLabel("1000 Hz");
        label1 = new JLabel("Period time (us):");
        label2 = new JLabel("Frequency:");
        set = new JButton("Set");
        content.add(label1);
        content.add(period);
        content.add(label2);
        content.add(frekvens);
        content.add(set);
        set.addActionListener(this);
        parent.createWindow(f);
        JPanel panel = new JPanel();
        Show_button = new JButton(new ImageIcon("images/tonegen.gif"));
        Show_button.setBorder(BorderFactory.createEmptyBorder());
        panel.add(Show_button);
        parent.addToTab(name, panel, false);
        Show_button.addActionListener(this);
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == Show_button) f.setVisible(!f.isVisible());
        if (evt.getSource() == set) {
            period_time = Integer.parseInt(period.getText());
            if (period_time == 0) running = false; else {
                running = true;
                frek = (int) ((double) (1 / (double) period_time) * 1000000);
                half_period = period_time / 2;
                frekvens.setText(String.valueOf(frek) + " Hz");
            }
        }
    }

    public void ioChangeNotify() {
    }

    public String getName() {
        return name;
    }

    public String[] getPinNames() {
        return pin_names;
    }

    public void clock(int project_clock) {
        if (project_clock % half_period == 0 && running) {
            state[0] = !state[0];
            parent.postPinUpdate(this);
        }
    }

    public void starting() {
    }

    public void stopping() {
    }
}
