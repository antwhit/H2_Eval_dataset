import java.awt.Color;

public interface IPlayer extends IPlayerInfo {

    /**
	 * Called after the player is constructed, telling it what board it has
	 * been assigned to play on, and what color they would like it to be.
	 * Note that because IPlayerInfo.getColor() returns an arbitrary color,
	 * you do not need to return the color you are assigned.  Just be close :)
	 */
    public void joinBoard(IBoard board, Color color);

    /**
	 * Notifies this player that their turn has begun.
	 */
    public void beginTurn();

    /**
	 * Notifies this player that their turn has ended (i.e. their move 
	 * instructions have been received and processed).
	 */
    public void endTurn();
}
