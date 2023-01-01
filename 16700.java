public class CurvedLine extends Line {

    public CurvedLine(Point p1, Point p2, Point cp1, Point cp2) {
        super(p1, p2);
        this.cp1 = cp1;
        this.cp2 = cp2;
    }

    Point cp1, cp2;

    public void moveBy(int dx, int dy) {
    }
}
