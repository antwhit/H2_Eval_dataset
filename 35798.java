import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

/** Constructs a rectangle object */
public class WBRectangle extends WBShape {

    public WBRectangle(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    public WBRectangle() {
    }

    @Override
    public String asString() {
        String result = "";
        return result + ";Rectangle";
    }

    /** Creates a copy of Rectangle object */
    public Object clone() {
        WBRectangle r = new WBRectangle(x1, y1, x2, y2);
        r.setLocation(getLocation());
        return r;
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawRect(0, 0, Math.abs(x2 - x1) - 1, Math.abs(y2 - y1) - 1);
    }
}
