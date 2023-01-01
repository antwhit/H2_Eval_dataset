import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;

public class History extends JPanel {

    private final Game game;

    public History(int level, Game gam) {
        this.setSize(new Dimension(500, 500));
        this.setVisible(true);
        this.requestFocus();
        this.game = gam;
        this.setBackground(Color.black);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel label;
        JTextPane tp = new JTextPane();
        try {
            URL url = new URL("file:history/level" + level + ".html");
            tp.setPage(url);
        } catch (Exception e) {
            try {
                URL url = new URL("file:history/default.html");
                tp.setPage(url);
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
        this.add(tp);
        tp.setForeground(Color.white);
        tp.setBackground(Color.black);
        JButton bBegin = new JButton("Commencer");
        bBegin.setBackground(Color.black);
        bBegin.setForeground(Color.white);
        this.add(bBegin);
        bBegin.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                setEnabled(false);
                game.displayComp();
            }
        });
        repaint();
    }
}
