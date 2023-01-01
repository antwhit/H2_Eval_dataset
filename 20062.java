import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MiniIcon extends JLabel implements MouseListener {

    Color color;

    String label;

    boolean entered;

    TableFrame parent;

    public MiniIcon(String label, Color color, TableFrame parent) {
        super();
        this.parent = parent;
        Font font = new Font("Verdana", Font.BOLD, 12);
        this.entered = false;
        this.addMouseListener(this);
        this.setFont(font);
        this.setText(" ");
        this.setHorizontalTextPosition(JLabel.RIGHT);
        this.setVerticalTextPosition(JLabel.CENTER);
        this.setOpaque(true);
        this.color = color;
        this.label = label;
        this.setPreferredSize(new Dimension(18, 18));
        this.setBorder(BorderFactory.createEmptyBorder());
        repaint();
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(Color.white);
        g2.fill(new Ellipse2D.Double(0, 0, 18, 18));
        g2.setPaint(new Color(0, 0, 0, 50));
        g2.fill(new Ellipse2D.Double(0, 0, 18, 18));
        GradientPaint redtowhite = new GradientPaint(5, 18, color, 18, 5, Color.WHITE);
        g2.setPaint(redtowhite);
        g2.fill(new Ellipse2D.Double(1, 1, 15, 15));
        if (entered) {
            g2.setPaint(new Color(0, 0, 0, 70));
            if (label.equals(">")) {
                Font font = new Font("Times", Font.BOLD, 17);
                g2.setFont(font);
                g2.drawString(label, 5, 15);
            } else if (label.equals("+")) {
                Font font = new Font("Times", Font.BOLD, 17);
                g2.setFont(font);
                g2.drawString(label, 4, 15);
            } else if (label.equals("-")) {
                Font font = new Font("Times", Font.BOLD, 22);
                g2.setFont(font);
                g2.drawString(label, 4, 15);
            } else if (label.equals("<")) {
                Font font = new Font("Times", Font.BOLD, 17);
                g2.setFont(font);
                g2.drawString(label, 5, 15);
            }
        }
    }

    public void mouseClicked(MouseEvent arg0) {
        if (label.equals("+")) {
            parent.getTable().maximize();
            parent.maximize();
        } else if (label.equals("-")) {
            parent.getTable().minimize();
            parent.minimize();
        } else if (label.equals(">")) {
            parent.unsummarize();
        } else if (label.equals("<")) {
            parent.summarize();
        }
    }

    public void mouseEntered(MouseEvent arg0) {
        entered = true;
        repaint();
    }

    public void mouseExited(MouseEvent arg0) {
        entered = false;
        repaint();
    }

    public void mousePressed(MouseEvent arg0) {
        mouseClicked(arg0);
    }

    public void mouseReleased(MouseEvent arg0) {
    }
}
