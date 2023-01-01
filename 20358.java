import java.util.ArrayList;

/**
 * Variant of ArrayList with typed get() method and convenient lookup.
 */
public class MoveList extends ArrayList {

    private IBoard _board;

    public MoveList(IBoard board) {
        _board = board;
    }

    /**
	 * Make an identical list, pointing to the same data (not to copies of the 
	 * data).
	 */
    public MoveList(MoveList copy) {
        _board = copy._board;
        for (int i = 0; i < copy.size(); i++) {
            add(copy.getMove(i));
        }
    }

    /**
	 * Adds move only if it is as good or better than everything in the list.
	 * If it is better, the existing elements are removed in favor of this move.
	 * @return true if we were as good or better.  false if the list already 
	 * contained better moves.
	 */
    public boolean addBest(Move move) {
        int score = move.score();
        for (int i = size() - 1; i >= 0; i--) {
            Move m = getMove(i);
            if (m.score() < score) remove(i); else if (m.score() > score) return false;
        }
        super.add(move);
        return true;
    }

    public Move getMove(int index) {
        return (Move) get(index);
    }

    public boolean contains(int row, int col) {
        return find(row, col) >= 0;
    }

    public void removeIfContains(int row, int col) {
        int i;
        do {
            i = find(row, col);
            if (i >= 0) remove(i);
        } while (i >= 0);
    }

    /**
	 * Find the index of the move in the list at the given coordinates.
	 * Note that there's nothing stopping the list from containing that 
	 * coordinate more than once.  This just finds the first instance.
	 * @return the index of the first instance, or -1 if none are found.
	 */
    public int find(int row, int col) {
        row = modR(row);
        col = modC(col);
        for (int i = 0; i < size(); i++) {
            Move m = getMove(i);
            if (m.row() == row && m.col() == col) return i;
        }
        return -1;
    }

    protected int modR(int r) {
        while (r < 0) r += _board.getRowCount();
        return r;
    }

    protected int modC(int c) {
        while (c < 0) c += _board.getColCount();
        return c;
    }
}
