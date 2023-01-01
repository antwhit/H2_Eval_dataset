import java.awt.Point;

/**
 * A HiResPoint is like a simplified version of java.awt.Point, but
 * it maintains its coordinates as doubles instead of integers.
 */
public class HiResPoint {

    /**
     * The x and y coordinates of this point
     */
    public double x;

    public double y;

    /**
     * Construct a new point whose coorinates are (0.0, 0.0)
     */
    public HiResPoint() {
        x = 0.0;
        y = 0.0;
    }

    /**
     * Construct a new point with the specified set of coordinates
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public HiResPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Construct a new HiResPoint from an existing one.
     *
     * @param p the existing HiResPoint
     */
    public HiResPoint(HiResPoint p) {
        this.x = p.x;
        this.y = p.y;
    }

    /**
     * Construct a new HiResPoint from the given java.awt.point
     *
     * @param p the existing java.awt.Point
     */
    public HiResPoint(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    /**
     * Return a string representation of this HiResPoint.
     *
     * @return A string representation, suitable for debugging
     */
    public String toString() {
        return "HiResPoint: [" + this.x + ", " + this.y + "]";
    }

    /**
     * Test to see if the given point has the same x and y coordinates as this one
     *
     * @param p the other HiResPoint
     * @return true if the coordinates match, false otherwise
     */
    public boolean equals(HiResPoint p) {
        if (this.x == p.x && this.y == y) return true;
        return false;
    }

    /**
     * Create a new java.awt.Point from this HiResPoint, where the coordinate
     * of the awt point are the rounded coordinates of this point
     *
     * @return a new java.awt.Point
     */
    public Point pointValue() {
        return new Point(((int) Math.round(this.x)), ((int) Math.round(this.y)));
    }
}
