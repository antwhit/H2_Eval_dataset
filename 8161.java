import java.applet.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;

public class Driver extends Applet implements Runnable {

    int x_pos = 30;

    int y_pos = 100;

    int x_speed = 0;

    int y_speed = 0;

    int radius = 20;

    int appletsize_x = 300;

    int appletsize_y = 300;

    private Graphics dbg;

    public void init() {
        setBackground(Color.blue);
    }

    public void start() {
        Thread th = new Thread(this);
        th.start();
    }

    public void stop() {
    }

    public void destroy() {
    }

    public boolean keyDown(Event e, int key) {
        if (key == Event.LEFT) {
            x_speed = -1;
        } else if (key == Event.RIGHT) {
            x_speed = 1;
        } else if (key == Event.UP) {
            y_speed = -1;
            x_speed = -1;
        } else if (key == Event.DOWN) {
            y_speed = 1;
            x_speed = 1;
        } else if (key == 32) {
            x_speed = 0;
            y_speed = 0;
        } else {
            System.out.println("Charakter: " + (char) key + " Integer Value: " + key);
        }
        return true;
    }

    public void run() {
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        while (true) {
            if (x_pos > appletsize_x - radius) {
                x_speed = -1;
            } else if (x_pos < radius) {
                x_speed = +1;
            } else if (y_pos > 100) {
                y_speed = -1;
            } else if (y_pos < 0) {
                y_speed = 1;
            }
            x_pos += x_speed;
            y_pos += y_speed;
            repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
            }
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        }
    }

    public void update(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, this.getSize().width, this.getSize().height);
        g.setColor(getForeground());
        paint(g);
    }

    public void paint(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(x_pos - radius, y_pos - radius, 2 * radius, 2 * radius);
    }
}
