import java.awt.*;

public class gpsLabel extends Label {

    public gpsLabel() {
    }

    public gpsLabel(String str) {
        super(str);
    }

    public void paint(Graphics g) {
        int width = getSize().width;
        int height = getSize().height;
        g.setPaintMode();
        g.setColor(Color.white);
        g.draw3DRect(0, 2, width - 1, height - 5, false);
    }
}
