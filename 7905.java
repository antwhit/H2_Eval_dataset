import java.text.DecimalFormat;

public class Point extends Polygons {

    private double x;

    private double y;

    public double area() {
        return 0;
    }

    public double perimeter() {
        return 0;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point getCenter() {
        return new Point(this.x, this.y);
    }

    public void setCenter(Point pt) {
        this.x = pt.x;
        this.y = pt.y;
    }

    /**
 * @param x2
 * @param y2
 * @return
 */
    public String toString() {
        DecimalFormat f = new DecimalFormat(Figures.pattern);
        StringBuilder FigureString = new StringBuilder();
        FigureString.append("x:").append(f.format(getX())).append(" ").append("y:").append(f.format(getY())).append(" ");
        FigureString.append("Perimeter: ").append(f.format(this.perimeter())).append(" ").append("Area:").append(f.format(this.area())).append(" ").append("WCenterX:").append(f.format(this.x)).append(" ").append("WCenterY:").append(f.format(this.y));
        String point = new String(FigureString);
        return point;
    }

    double getY() {
        return this.y;
    }

    double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
