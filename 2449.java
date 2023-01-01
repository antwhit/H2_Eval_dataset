import java.awt.*;
import javax.swing.*;

public class VehicleDirectionDisplay extends JComponent {

    private double direction;

    private boolean showLine;

    public VehicleDirectionDisplay(double direction, boolean park) {
        super();
        setPreferredSize(new Dimension(50, 50));
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
        this.direction = direction;
        if (park) showLine = false; else showLine = true;
    }

    public void updateDirection(double newDirection, boolean park) {
        if (newDirection != direction) {
            direction = newDirection;
            if (park) showLine = false; else showLine = true;
            repaint();
        }
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, 50, 50);
        g.setColor(new Color(0, 62, 0));
        g.fillOval(3, 3, 43, 43);
        g.setColor(Color.green);
        g.drawOval(3, 3, 43, 43);
        g.drawRect(25, 25, 1, 1);
        Font tempFont = new Font("Helvetica", Font.PLAIN, 9);
        g.setFont(tempFont);
        FontMetrics tempMetrics = getFontMetrics(tempFont);
        int fontHeight = tempMetrics.getAscent();
        int nWidth = tempMetrics.charWidth('N');
        g.drawString("N", 25 - (nWidth / 2), (fontHeight / 2) + 10);
        int sWidth = tempMetrics.charWidth('S');
        g.drawString("S", 25 - (sWidth / 2), 39 + (fontHeight / 2));
        int wWidth = tempMetrics.charWidth('W');
        g.drawString("W", 10 - (wWidth / 2), 25 + (fontHeight / 2));
        int eWidth = tempMetrics.charWidth('E');
        g.drawString("E", 39 - (eWidth / 2), 25 + (fontHeight / 2));
        if (showLine) {
            double hyp = (double) (22);
            int newX = (int) Math.round(hyp * Math.sin(direction));
            int newY = -1 * (int) Math.round(hyp * Math.cos(direction));
            g.drawLine(25, 25, newX + 25, newY + 25);
        }
    }
}
