import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class KlickSameTy extends MIDlet {

    private MainMenu menu;

    private GameScreen gameScreen;

    private RMSGameScores scores;

    private HighScore highscore;

    private FillScore fillScore;

    public KlickSameTy() {
        menu = new MainMenu(this);
        scores = new RMSGameScores();
        fillScore = new FillScore(this);
    }

    public String showHighscore() {
        return scores.getScores();
    }

    public void addScore(String who) {
        if (currentScore > 0) scores.addScore(currentScore, who);
        highscore();
    }

    public void highscore() {
        highscore = new HighScore(this);
        Display.getDisplay(this).setCurrent(highscore);
    }

    private byte gameType;

    private int currentScore = -1;

    public void gameOver(int score) {
        Alert alert = new Alert("", "Game Over! \nYour Score is: " + score, null, AlertType.INFO);
        alert.setTimeout(Alert.FOREVER);
        if (score > scores.getMinScore()) {
            currentScore = score;
            Display.getDisplay(this).setCurrent(alert, fillScore);
        } else {
            Display.getDisplay(this).setCurrent(alert, menu);
        }
    }

    public void mainMenu() {
        Display.getDisplay(this).setCurrent(menu);
    }

    public void close() {
        try {
            destroyApp(true);
            notifyDestroyed();
        } catch (MIDletStateChangeException e) {
        }
    }

    public void startApp() throws MIDletStateChangeException {
        Display.getDisplay(this).setCurrent(menu);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) throws MIDletStateChangeException {
    }

    public void activateGameScreen() {
        gameScreen = new GameScreen(this, GameScreen.SIMPLE);
        Display.getDisplay(this).setCurrent(gameScreen);
    }

    public void activateSecondGame() {
        gameScreen = new GameScreen(this, GameScreen.LEVELS);
        Display.getDisplay(this).setCurrent(gameScreen);
    }
}
