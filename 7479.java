/**
 * A tényleges ember játékost reprezentálja. 
 * 
 */
public class HumanPlayer extends Player {

    /**
     * @param playerName Az új játékos neve.
     * @param changeSnakeHigher  Az aktiális irányított kígyó indexét (actualFigure) növelő vezérlőkarakter.
     * @param changeSnakeLower  Az aktiális irányított kígyó indexét (actualFigure) csökkentő vezérlőkarakter.
     * @param initControlKeys Vezérlőkarakterek tömbje
     * 
     */
    public HumanPlayer(String playerName, int[] initControlKeys, int changeSnakeHigher, int changeSnakeLower) {
        super(playerName, initControlKeys, changeSnakeHigher, changeSnakeLower);
    }

    /**
     * @param playerName Az új játékos neve.
     * @param changeSnakeHigher  Az aktiális irányított kígyó indexét (actualFigure) növelő vezérlőkarakter.
     * @param changeSnakeLower  Az aktiális irányított kígyó indexét (actualFigure) csökkentő vezérlőkarakter.
     * @param controlKeysCount A játékos irányítóbillentyűihez tartozó karaktereket tartalmazó tömb mérete.
     * 
     */
    public HumanPlayer(String playerName, int changeSnakeHigher, int changeSnakeLower, int controlKeysCount) {
        super(playerName, changeSnakeHigher, changeSnakeLower, controlKeysCount);
    }
}
