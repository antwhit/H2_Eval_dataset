import java.io.IOException;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author Zolko
 */
public class Ufo extends Enemy {

    private int pos = 0;

    private boolean left = true;

    private Image bullet;

    public Ufo(Image image, int frame_width, int frame_height, boolean left, Image bullet) {
        super(image, frame_width, frame_height);
        this.hp = 2;
        this.left = left;
        this.bullet = bullet;
    }

    public void update() {
        if (pos == -10 || pos == 10) {
            left = !left;
        }
        if (left) {
            this.move(-1, 1);
            pos--;
        } else {
            this.move(1, 1);
            pos++;
        }
    }

    public Sprite shoot() {
        if (this.getY() > 0) {
            Sprite shot = new Sprite(bullet, 8, 8);
            shot.move(this.getX() + 6, this.getY() + 16);
            return shot;
        }
        return null;
    }

    public int getValue() {
        return 100;
    }
}
