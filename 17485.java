import java.awt.*;
import java.awt.image.*;

public class Map {

    private Polygon[] objs;

    private VolatileImage image;

    private boolean valid;

    public Map(Polygon[] p, Dimension size) {
        objs = p;
        image = GraphicsEnvironment.getLocalGraphicsEnvironment().createVolatileImage(size.getWidth(), size.getHeight());
        Graphics g = image.createGraphics();
        for (int i = 0; i < objs.length; i++) {
            image.createGraphics().draw(objs[i]);
        }
    }

    public VolatileImage getImage() {
        return image;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean v) {
        valid = v;
    }
}
