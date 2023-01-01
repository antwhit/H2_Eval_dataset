import java.awt.*;
import java.applet.*;

public class Pog extends Panel {

    PogCanvas pogCanvas;

    Color color;

    public Pog(Color c) {
        super();
        this.color = c;
        pogCanvas = new PogCanvas(color);
        setLayout(new BorderLayout());
        add("Center", pogCanvas);
    }

    public Dimension preferredSize() {
        return new Dimension(100, 100);
    }

    public Dimension minimumSize() {
        return new Dimension(25, 25);
    }

    public static void main(String[] args) {
        Frame myFrame = new Frame("Pog");
        Panel myPanel = new Panel();
        Color c = new Color(100, 50, 255);
        Pog myPog = new Pog(c);
        myFrame.setLayout(new BorderLayout());
        myPanel.setLayout(new BorderLayout());
        myPanel.add("Center", myPog);
        myFrame.add("Center", myPanel);
        myFrame.resize(500, 500);
        myFrame.show();
    }
}

class PogCanvas extends Canvas {

    Color color;

    public PogCanvas(Color c) {
        super();
        this.color = c;
    }

    public void paint(Graphics g) {
        if (g != null) {
            g.setColor(color);
            g.fill3DRect(10, 10, size().width - 20, size().height - 20, true);
            int width = size().width;
            int height = size().height;
            g.setColor(Color.BLACK);
            g.drawLine(20, 20, width - 40, height - 40);
            g.drawLine(width - 40, 20, 20, height - 40);
        }
    }

    public void update(Graphics g) {
        paint(g);
    }

    public Dimension preferredSize() {
        return new Dimension(200, 200);
    }

    public Dimension minimumSize() {
        return new Dimension(25, 25);
    }
}
