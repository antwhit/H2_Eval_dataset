import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

public class RectProp {

    Color c;

    Rectangle r;

    Boolean black;

    int tsz;

    String s;

    int id;

    /**constructor*/
    RectProp(Rectangle r, Color c, Boolean black) {
        this.black = black;
        this.r = r;
        this.c = c;
    }

    /**sets the ID*/
    void setID(int i) {
        id = i;
    }

    /**gets the id*/
    int getID() {
        return id;
    }

    /**return position of sphere*/
    Point getPos() {
        return r.getLocation();
    }

    void set(String s) {
        this.s = s;
    }

    String getS() {
        return s;
    }

    Boolean getBlack() {
        return black;
    }

    void setTsz(int tsz) {
        this.tsz = tsz;
    }

    int getTsz() {
        return tsz;
    }
}
