import javax.swing.ImageIcon;
import java.util.*;

public class Piece {

    private Location loc;

    private ImageIcon image;

    private boolean isWhite;

    private boolean isHighlightedRed;

    public Piece() {
        loc = new Location(0, 0);
    }

    public Piece(int x, int y, boolean isWhite1) {
        loc = new Location(x, y);
        isWhite = isWhite1;
        isHighlightedRed = false;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon i) {
        image = i;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public boolean isSameColorAs(Piece p) {
        if (p instanceof Empty) {
            return false;
        }
        if (p.isWhite() == isWhite) {
            System.out.println("ALL THE SAME COLOR");
            return true;
        } else {
            return false;
        }
    }

    public void highlightRed() {
        System.out.println("I am the problem");
    }

    public void deHighlight() {
    }

    public boolean isHighlightedRed() {
        return isHighlightedRed;
    }

    public void setIsHighlightedRed(boolean bool) {
        isHighlightedRed = bool;
    }

    public void updateImage() {
    }

    public void removeOutOfBounds(ArrayList<Location> list) {
        for (int k = 0; k < list.size(); k++) {
            if (list.get(k).getX() < 0 || list.get(k).getX() > 7 || list.get(k).getY() < 0 || list.get(k).getY() > 7) {
                list.remove(k);
                k--;
            }
        }
    }

    public ArrayList<Location> getPossibleMoves(Spot[][] spot) {
        return new ArrayList<Location>();
    }

    public boolean isBackgroundWhite() {
        if (getX() % 2 == 0 && getY() % 2 == 0) {
            return true;
        } else if (getX() % 2 != 0 && getY() % 2 != 0) {
            return true;
        } else {
            return false;
        }
    }

    public void setMoved() {
    }

    public void removeSameColor(ArrayList<Location> list, Spot[][] spot) {
        for (int k = 0; k < list.size(); k++) {
            if (spot[getX()][getY()].getPiece().isSameColorAs(spot[list.get(k).getX()][list.get(k).getY()].getPiece())) {
                list.remove(k);
                k--;
            }
        }
    }

    public void moveTo(int x, int y) {
        loc.setLoc(x, y);
        if (this instanceof Pawn) {
            setMoved();
        }
    }

    public int getX() {
        return loc.getX();
    }

    public int getY() {
        return loc.getY();
    }
}
