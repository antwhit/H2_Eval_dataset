import java.awt.*;
import java.awt.event.*;
import ar.com.jkohen.awt.ImageButton;
import ar.com.jkohen.util.Resources;

public class Box extends Dialog implements ActionListener, KeyListener, WindowListener {

    private EIRC eirc;

    private Resources res;

    private TextField input;

    private int click = 0;

    public Box(EIRC eirc, Frame f, String title, String label1, String label2) {
        this(eirc, f, title, label1, null, null, false);
    }

    public Box(EIRC eirc, Frame f, String title, String label1, String label2, String field, boolean pw_field) {
        super(f, true);
        this.eirc = eirc;
        setFont(eirc.getFont());
        addWindowListener(this);
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(gb);
        gbc.insets = new Insets(2, 2, 2, 2);
        ImageButton b;
        Label l;
        l = new Label(label1);
        if (label2 != null) gbc.gridwidth = 2;
        gbc.gridy = 0;
        gb.setConstraints(l, gbc);
        add(l);
        l = new Label(label2 + " :");
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gb.setConstraints(l, gbc);
        if (label2 != null) add(l);
        input = new TextField(16);
        if (pw_field) input.setEchoChar('*');
        if (label2 != null) input.setText(field);
        input.addActionListener(this);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gb.setConstraints(input, gbc);
        if (label2 != null) add(input);
        Panel p = new Panel();
        gbc.gridy++;
        if (label2 != null) gbc.gridwidth = 2;
        gb.setConstraints(p, gbc);
        add(p);
        p.setLayout(gb);
        b = new ImageButton(res.getString("ok"));
        b.setActionCommand("ok");
        b.addActionListener(this);
        gbc.gridy = 0;
        if (label2 != null) gbc.gridwidth = 1; else gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gb.setConstraints(b, gbc);
        p.add(b);
        b = new ImageButton(res.getString("cancel"));
        b.setActionCommand("cancel");
        b.addActionListener(this);
        gbc.gridwidth = 1;
        gb.setConstraints(b, gbc);
        if (label2 != null) p.add(b);
        input.addKeyListener(this);
        input.requestFocus();
        setBackground(eirc.getBackground());
        setForeground(eirc.getForeground());
        setTextBackground(eirc.getTextBackground());
        setTextForeground(eirc.getTextForeground());
        pack();
        setTitle(title);
        setLocation((f.getSize().width - getSize().width) / 2 + f.getLocation().x, (f.getSize().height - getSize().height) / 2 + f.getLocation().y);
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ev) {
        String action = ev.getActionCommand();
        if (action.equals("ok") || ev.getSource() == input) {
            click = 1;
            dispose();
        } else if (action.equals("cancel")) {
            click = 2;
            dispose();
        }
    }

    public void keyTyped(KeyEvent ke) {
    }

    public void keyReleased(KeyEvent ke) {
    }

    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
            click = 2;
            dispose();
        }
    }

    public int getResult() {
        return (click);
    }

    public String getString() {
        return (input.getText());
    }

    public void setTextBackground(Color c) {
        input.setBackground(c);
    }

    public void setTextForeground(Color c) {
        input.setForeground(c);
    }

    public void windowOpened(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
        this.dispose();
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }
}
