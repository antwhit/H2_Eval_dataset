import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * @author Matthias
 * 
 */
public class Warships extends BasicGame {

    private int fps = 30;

    private long delta;

    private long last;

    private boolean firstRun = true;

    public Warships() {
        super("Warships");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        try {
            this.delta = System.nanoTime() - this.last;
            if (!firstRun) {
                Thread.sleep(1000 / fps - this.delta / ((long) 1e6));
            } else {
                firstRun = false;
            }
        } catch (Exception e) {
        }
        this.last = System.nanoTime();
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        g.drawString("Hello, Slick world!", 0, 100);
    }

    public static void main(String[] args) {
        try {
            AppGameContainer app = new AppGameContainer(new Warships());
            app.setDisplayMode(800, 640, false);
            app.setShowFPS(true);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
