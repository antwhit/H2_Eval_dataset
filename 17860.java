import java.text.DecimalFormat;

public class Ring implements Figures {

    private double radius;

    private Point x1;

    public double area() {
        double area = (Math.pow(radius, 2) * 2);
        return area;
    }

    public double perimeter() {
        double perimeter = radius * 2 * Math.PI;
        return perimeter;
    }

    public Point getCenter() {
        return x1;
    }

    public Ring(double r, Point p1) {
        this.radius = r;
        this.x1 = p1;
    }

    public double getRadius() {
        return radius;
    }

    public String toString() {
        StringBuilder figureString = new StringBuilder();
        DecimalFormat f = new DecimalFormat(Figures.pattern);
        figureString.append("Center:").append(" ").append("x:").append(f.format(x1.getX())).append(" ").append("y:").append(f.format(x1.getY())).append(" ");
        figureString.append("Perimeter:").append(f.format(this.perimeter())).append(" ").append("Radius:").append(f.format(this.radius)).append(" ").append("Area:").append(f.format(this.area())).append(" ").append("CenterX:").append(f.format(this.x1.getX())).append("CenterY:").append(f.format(this.x1.getY()));
        String ring = new String(figureString);
        return ring;
    }
}
