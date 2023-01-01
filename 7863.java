import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import net.phys2d.raw.Body;
import net.phys2d.raw.shapes.Box;

public class Player {

    public int x, y;

    public Body poly;

    private SpriteSheet run;

    public Animation fallback;

    public Player(int x, int y) throws SlickException {
        this.y = y;
        this.x = x;
        run = new SpriteSheet("data/samus.png", 48, 48);
        fallback = new Animation();
        for (int frame = 0; frame < 10; frame++) {
            fallback.addFrame(run.getSprite(frame, 0), 180);
        }
        poly = new Body("Mover1", new Box(50.0f, 50.0f), 1.0f);
        poly.setPosition(x, y);
    }

    public void update(int delta) {
        fallback.update(delta);
        this.x = (int) poly.getPosition().getX() - 25;
        this.y = (int) poly.getPosition().getY() - 25;
    }

    public void draw(Graphics g) {
        g.drawAnimation(fallback, x, y);
    }
}
