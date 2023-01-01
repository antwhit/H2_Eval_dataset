import javax.swing.Icon;
import java.awt.*;
import java.awt.font.*;
import java.util.*;

public class TextIcon implements Icon {

    protected Font font;

    protected String text;

    protected int width;

    protected int height;

    public TextIcon(Font font, String text, int width, int height) {
        this.font = font;
        this.text = text;
        this.width = width;
        this.height = height;
    }

    public int getIconHeight() {
        return height;
    }

    public int getIconWidth() {
        return width;
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {
        StringTokenizer st = new StringTokenizer(text, "\n");
        Shape originalClip = g.getClip();
        g.clipRect(x, y, width, height);
        Font of = g.getFont();
        Color oc = g.getColor();
        g.setColor(new Color(255, 255, 255));
        g.fillRect(x, y, width, height);
        g.setColor(oc);
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();
        int starty = y;
        while (st.hasMoreTokens()) {
            String line = st.nextToken();
            LineMetrics lm = fm.getLineMetrics(line, g);
            g.drawString(line, x, (int) (y + lm.getAscent()));
            y += lm.getHeight();
            if (y - starty > height) break;
        }
        g.setFont(of);
        g.setClip(originalClip);
    }
}
