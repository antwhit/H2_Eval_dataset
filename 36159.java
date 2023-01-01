import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.*;
import org.newdawn.slick.*;
import mdes.slick.util.TimerAction;

/**
 * Created by IntelliJ IDEA.
 * User: Matko
 * Date: 20.8.2007
 * Time: 19:16:21
 * 
 */
public class Title extends BasicGameState {

    public static final int ID = 1;

    private StateBasedGame game;

    private AngelCodeFont font;

    private Image titleImage;

    private TimerAction timer;

    public Title() {
        super();
    }

    public int getID() {
        return ID;
    }

    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.game = stateBasedGame;
        this.titleImage = new Image("GFX/Title/title.png");
        this.font = new AngelCodeFont("GFX/Title/titlefont.fnt", "GFX/Title/titlefont_00.png");
        timer = new TimerAction(5000);
        game.enterState(1, new FadeInTransition(Color.black), new EmptyTransition());
    }

    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        titleImage.draw(220, 120);
        this.font.drawString(370, 500, "SourceForge group");
    }

    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int time) throws SlickException {
        if (gameContainer.getInput().isMouseButtonDown(0)) {
            game.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }
        if (timer.update(time)) {
            game.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }
    }
}
