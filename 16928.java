public class nPolygon extends Polygons {

    Point[] Corners;

    int c;

    public double area() {
        return 0;
    }

    public Point getCenter() {
        return null;
    }

    public double perimeter() {
        return 0;
    }

    public nPolygon(int c, Point[] Corners) {
        this.c = c;
        this.Corners = new Point[c];
    }

    public String toString() {
        StringBuilder figureString = new StringBuilder();
        for (int i = 0; i < Corners.length; i++) {
            figureString.append(" x").append(" ").append(this.Corners[i].getX()).append(" y").append(" ").append(this.Corners[i + 1].getY());
        }
        String nPolygon = new String(figureString);
        return nPolygon;
    }
}
