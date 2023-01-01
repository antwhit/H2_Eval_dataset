import javax.microedition.lcdui.*;

public class Piece {

    public static final int POWN = 0;

    public static final int KNIGHT = 1;

    public static final int BISHOP = 2;

    public static final int ROOK = 3;

    public static final int QUEEN = 4;

    public static final int KING = 5;

    public static final int FREE = 6;

    public static final int BLACK = 0;

    public static final int WHITE = 1;

    public int type = POWN;

    public int color = WHITE;

    private ChessCell cell;

    public final int PLAYABLE = 0;

    public final int NOT_PLAYABLE = 1;

    public final int POWN_SWAPPING = 2;

    public int state = PLAYABLE;

    public boolean selected = false;

    public boolean threated = false;

    public boolean king_moved = false;

    public int initialMove = 0;

    public int stepRange;

    public boolean moveStraight;

    public boolean moveDiagonal;

    public boolean moveBackward;

    boolean killStraight;

    public Piece(int type, int color) {
        this.color = color;
        setType(type);
    }

    void setType(final int pieceType) {
        type = pieceType;
        if (type == POWN) {
            stepRange = 2;
            moveStraight = true;
            moveDiagonal = true;
            moveBackward = false;
            killStraight = false;
        } else if (type == KNIGHT) {
            moveStraight = false;
            moveDiagonal = false;
            moveBackward = true;
            killStraight = true;
            stepRange = 2;
        } else if (type == BISHOP) {
            moveStraight = false;
            moveDiagonal = true;
            moveBackward = true;
            killStraight = true;
            stepRange = 10;
        } else if (type == ROOK) {
            moveStraight = true;
            moveDiagonal = false;
            moveBackward = true;
            killStraight = true;
            stepRange = 10;
        } else if (type == QUEEN) {
            moveStraight = true;
            moveDiagonal = true;
            moveBackward = true;
            killStraight = true;
            stepRange = 10;
        } else if (type == KING) {
            moveStraight = true;
            moveDiagonal = true;
            moveBackward = true;
            killStraight = true;
            stepRange = 1;
        }
    }

    public void setCell(ChessCell c) {
        cell = c;
    }

    public ChessCell getCell() {
        return cell;
    }

    public int distanceX(final int cellWeWantToMoveItX) {
        return Math.abs(cellWeWantToMoveItX - cell.x);
    }

    public int distanceY(final int cellWeWantToMoveItY) {
        return Math.abs(cellWeWantToMoveItY - cell.y);
    }
}
