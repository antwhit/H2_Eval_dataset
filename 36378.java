import javax.swing.*;
import java.awt.event.*;

class StartServer implements ActionListener {

    MultiServer ms;

    JButton startb, stopb;

    public void actionPerformed(ActionEvent e) {
        JButton jb = (JButton) (e.getSource());
        try {
            if ((jb.getText()).equals("Start Server")) {
                new Thread(ms).start();
                startb.setEnabled(false);
                stopb.setEnabled(true);
            } else {
                startb.setEnabled(true);
                stopb.setEnabled(false);
                ms.endserver();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void init(MultiServer ms1) {
        ms = ms1;
        JFrame jf = new JFrame("Server");
        JPanel p = new JPanel();
        startb = new JButton("Start Server");
        stopb = new JButton("Stop Server");
        startb.addActionListener(this);
        stopb.addActionListener(this);
        p.add(startb);
        p.add(stopb);
        jf.getContentPane().add(p);
        jf.setSize(120, 100);
        jf.setResizable(false);
        jf.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent we) {
                ms.endserver();
                System.exit(0);
            }
        });
        jf.setVisible(true);
        stopb.setEnabled(false);
    }
}

class Servergui {

    public static void main(String args[]) {
        MultiServer ms = new MultiServer();
        StartServer ss = new StartServer();
        ss.init(ms);
    }
}
