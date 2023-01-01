import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class RectangleObject implements IDrawingObject {

    Color myColor;

    Point p1, p2;

    public RectangleObject(Point p1, Point p2, Color c) {
        myColor = c;
        this.p1 = p1;
        this.p2 = p2;
    }

    public void drawMe(Graphics g) {
        Color tempColor = g.getColor();
        g.setColor(myColor);
        g.drawRect(Math.min(p1.x, p2.x), Math.min(p1.y, p2.y), Math.abs(p1.x - p2.x), Math.abs(p1.y - p2.y));
        g.setColor(tempColor);
    }

    public boolean isPointInObject(Point p) {
        return p.x <= Math.max(p1.x, p2.x) && p.x >= Math.min(p1.x, p2.x) && p.y >= Math.min(p1.y, p2.y) && p.y <= Math.max(p1.y, p2.y);
    }

    public int getHeight() {
        return Math.abs(p1.y - p2.y);
    }

    public int getTop() {
        return Math.min(p1.y, p2.y);
    }

    public int getWidth() {
        return Math.abs(p1.x - p2.x);
    }

    public int getLeft() {
        return Math.min(p1.x, p2.x);
    }

    public void setmycolor(Color c) {
        myColor = c;
    }
}
