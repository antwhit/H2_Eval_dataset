import java.awt.geom.Point2D;

/**
 * Pontot megval�s�t� oszt�ly
 */
public class Point extends Point2D {

    private double x;

    private double y;

    /**
	 * Konstruktor (default)
	 */
    public Point() {
        this(0, 0);
    }

    /**
	 * Konstruktor (k�t pontj�val)
	 */
    public Point(double x, double y) {
        this.setX(x);
        this.setY(y);
    }

    /**
	 * A pont mozgat�s�t v�gz� f�ggv�ny
	 * @param vector Az eltol�s vektora
	 */
    public void move(Vector vector) {
        x = x + vector.getX();
        y = y + vector.getY();
    }

    /**
	 * @param x the x to set
	 */
    public void setX(double x) {
        this.x = x;
    }

    /**
	 * @return the x
	 */
    public double getX() {
        return x;
    }

    /**
	 * @param y the y to set
	 */
    public void setY(double y) {
        this.y = y;
    }

    /**
	 * @return the y
	 */
    public double getY() {
        return y;
    }

    /**
	 * @see java.awt.geom.Point2D#setLocation(double, double)
	 */
    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
	 * @return hibakeresesi kimenet ("X: 0.0 Y: 0.0")
	 */
    public String toString() {
        double x = getX();
        double y = getY();
        x = 100 * x;
        y = 100 * y;
        x = (double) Math.round(x);
        y = (double) Math.round(y);
        x = x / 100;
        y = y / 100;
        return (String) (x + " " + y);
    }

    /**
	 * Kivonja a pont koordinataibol a megadott pont koordinatait, ezzel egy elmozdulas vektort kepez.
	 * @param point
	 * @return Az elmozdulas vektora
	 */
    public Vector subtract(Point point) {
        return new Vector((getX() - point.getX()), (getY() - point.getY()));
    }
}
