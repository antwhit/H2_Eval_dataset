import java.awt.Graphics;
import java.awt.Color;
import java.awt.Component;
import javax.swing.border.*;

public class PaneBorder extends LineBorder {

    public PaneBorder(Color color) {
        super(color, 1, false);
    }

    public PaneBorder(Color color, int thickness) {
        super(color, thickness, false);
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Color oldColor = g.getColor();
        int i;
        g.setColor(lineColor);
        for (i = 0; i < thickness; i++) {
            g.setColor(Color.WHITE);
            g.drawRect(x + 1, y + 2, 0, height - 2);
            g.setColor(Color.lightGray.darker());
            g.drawRect(x, y + 2, 0, height - 2);
            g.setColor(g.getColor().brighter());
        }
        g.setColor(oldColor);
    }
}
