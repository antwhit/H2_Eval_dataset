import java.awt.Color;
import java.util.LinkedList;

public class ColorQueue {

    /** Creates a new instance of ColorQueue */
    static LinkedList<Color> colors;

    static {
        colors = new LinkedList<Color>();
        colors.add(new Color(180, 200, 230));
        colors.add(new Color(130, 190, 90));
        colors.add(new Color(230, 170, 60));
        colors.add(new Color(90, 230, 130));
        colors.add(new Color(70, 110, 180));
        colors.add(new Color(130, 130, 130));
    }

    public static Color getColor() {
        return colors.remove();
    }

    public static void addColor(Color color) {
        colors.add(color);
    }
}
