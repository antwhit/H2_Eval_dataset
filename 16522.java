import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class ImageMovePanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private BufferedImage img;

    private Rectangle selection;

    private double rotationAngle;

    public ImageMovePanel() {
        setOpaque(false);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setRotationAngle(0);
    }

    public void setImage(Point p, BufferedImage i) {
        img = i;
        int w = 0;
        int h = 0;
        if (img != null) {
            w = img.getWidth();
            h = img.getHeight();
        }
        selection = new Rectangle(p.x, p.y, w, h);
        repaint();
    }

    public void setRotationAngle(double angle) {
        rotationAngle = angle;
    }

    public void setSelectionBounds(Rectangle rect) {
        selection = rect;
        repaint();
    }

    public void paintComponent(Graphics gr) {
        Graphics2D g2d = (Graphics2D) gr;
        g2d.rotate(Math.toRadians(rotationAngle), img.getWidth() / 2, img.getHeight() / 2);
        if ((img != null) && (selection != null)) {
            int dx1 = selection.x;
            int dy1 = selection.y;
            int dx2 = selection.x + selection.width;
            int dy2 = selection.y + selection.height;
            g2d.drawImage(img, dx1, dy1, dx2, dy2, 0, 0, img.getWidth(), img.getHeight(), this);
        }
    }
}
