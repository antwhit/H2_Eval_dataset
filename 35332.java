import java.awt.*;
import java.util.*;

/**
 * This class represents a scoring zone. It can be pretty much any shape as long
 * as it can be defined using a polygon
 */
class Zone {

    private Polygon zoneShape;

    private int score;

    private String text;

    private Color color;

    /**
     * The main constructor for the zone
     */
    Zone(Polygon shape, Color col, int score, String text) {
        this.zoneShape = shape;
        this.color = col;
        this.score = score;
        this.text = text;
    }

    /**
     * Adjusts the shapes coordinates
     */
    public void adjustCoordinates(int xOffset, int yOffset) {
        for (int j = 0; j < zoneShape.npoints; ++j) {
            zoneShape.xpoints[j] += xOffset;
            zoneShape.ypoints[j] += yOffset;
        }
    }

    public int getScore() {
        return score;
    }

    /**
     * Returns whether a point falls within the zone bounds
     */
    public boolean inZone(Point p) {
        return zoneShape.contains(p);
    }

    /**
     * Returns whether an x, y coordinate falls within the zone bounds
     */
    public boolean inZone(int x, int y) {
        return zoneShape.contains(x, y);
    }

    /**
     * Draws the zone
     */
    public void drawZone(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Color oldC = g2.getColor();
        g2.setColor(color);
        g2.fill(zoneShape);
        g2.setColor(Color.black);
        g2.draw(zoneShape);
        g2.setColor(Color.black);
        FontMetrics fm = g.getFontMetrics();
        Rectangle zoneBounds = zoneShape.getBounds();
        int x = zoneBounds.x + (zoneBounds.width / 2) - fm.stringWidth(text) / 2;
        int y = zoneBounds.y + (zoneBounds.height / 2) + fm.getAscent() / 2;
        g2.drawString(text, x, y);
        g2.setColor(oldC);
    }
}
