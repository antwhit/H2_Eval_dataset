import java.awt.*;

public interface Sprite {

    public void drawSelf(Graphics g);

    public Rectangle getBounds();

    public boolean colidingWith(Sprite s);
}
