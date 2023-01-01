import java.util.Random;
import javax.microedition.lcdui.Graphics;

public class Food {

    public int x;

    public int y;

    public int size = 8;

    private Random r;

    public int maxX;

    public int maxY;

    public int minX;

    public int minY;

    public Food(int minY, int minX, int maxY, int maxX, int size) {
        r = new Random();
        this.maxX = maxX;
        this.maxY = maxY;
        this.minX = minX;
        this.minY = minY;
        this.size = size;
        reFood();
    }

    public void print(Graphics g) {
        g.setColor(100, 100, 100);
        g.fillRect(x, y, size, size);
        g.setColor(0, 0, 0);
    }

    public void reFood() {
        x = ((r.nextInt((maxX - minX) / (size + 2))) * (size + 2)) + minX + 1;
        y = ((r.nextInt((maxY - minY) / (size + 2))) * (size + 2)) + minY + 1;
    }
}
