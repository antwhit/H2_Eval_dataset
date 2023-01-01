import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import com.golden.gamedev.*;
import com.golden.gamedev.object.*;

/**
 * Sprite in action!!!
 *
 * Objective:
 * - show an animated sprite
 * - move the sprite based on key input
 */
public class Tutorial7_2 extends Game {

    AnimatedSprite sprite;

    Sprite projectile;

    GameFont font;

    /****************************************************************************/
    public void initResources() {
        BufferedImage[] images = getImages("resources/plane2.png", 3, 1);
        sprite = new AnimatedSprite(images, 275, 250);
        sprite.setAnimate(true);
        sprite.setLoopAnim(true);
        projectile = new Sprite(getImage("resources/projectile.png"), -50, -50);
        font = fontManager.getFont(getImages("resources/font.png", 20, 3), " !            .,0123" + "456789:   -? ABCDEFG" + "HIJKLMNOPQRSTUVWXYZ ");
    }

    public void update(long elapsedTime) {
        sprite.update(elapsedTime);
        projectile.update(elapsedTime);
        if (keyDown(KeyEvent.VK_LEFT)) sprite.moveX(-0.1 * elapsedTime);
        if (keyDown(KeyEvent.VK_RIGHT)) sprite.moveX(0.1 * elapsedTime);
        if (keyDown(KeyEvent.VK_UP)) sprite.moveY(-0.1 * elapsedTime);
        if (keyDown(KeyEvent.VK_DOWN)) sprite.moveY(0.1 * elapsedTime);
        if (keyPressed(KeyEvent.VK_CONTROL)) {
            projectile.setLocation(sprite.getX() + 16.5, sprite.getY() - 16);
            projectile.setVerticalSpeed(-0.5);
            playSound("resources/sound1.wav");
        }
        if (keyPressed(KeyEvent.VK_ESCAPE)) {
            finish();
        }
    }

    public void render(Graphics2D g) {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, getWidth(), getHeight());
        sprite.render(g);
        projectile.render(g);
        int spriteX = (int) sprite.getX();
        int spriteY = (int) sprite.getY();
        font.drawString(g, "POS: " + spriteX + ", " + spriteY, spriteX - 60, spriteY + 70);
        font.drawString(g, "ARROW KEY : MOVE", 10, 10);
        font.drawString(g, "CONTROL   : FIRE", 10, 30);
        font.drawString(g, "ESCAPE    : QUIT GAME", 10, 50);
    }

    /****************************************************************************/
    public static void main(String[] args) {
        GameLoader game = new GameLoader();
        game.setup(new Tutorial7_2(), new Dimension(640, 480), false);
        game.start();
    }
}
