import java.lang.Math.*;

public class Point3d {

    public double m_x, m_y, m_z;

    public Point3d(double x, double y, double z) {
        setX(x);
        setY(y);
        setZ(z);
    }

    public Point3d() {
        this(0, 0, 0);
    }

    public Point3d(Point3d otherPoint) {
        this(otherPoint.getX(), otherPoint.getY(), otherPoint.getZ());
    }

    public Point3d getDifference(Point3d otherPoint) {
        return new Point3d(otherPoint.getX() - getX(), otherPoint.getY() - getY(), otherPoint.getZ() - getZ());
    }

    public double getDistance(Point3d otherPoint) {
        Point3d temp = getDifference(otherPoint);
        return Math.sqrt(Math.pow(temp.getX(), 2) + Math.pow(temp.getY(), 2) + Math.pow(temp.getZ(), 2));
    }

    public double getX() {
        return m_x;
    }

    public double getY() {
        return m_y;
    }

    public double getZ() {
        return m_z;
    }

    public void setX(double x) {
        m_x = x;
    }

    public void setY(double y) {
        m_y = y;
    }

    public void setZ(double z) {
        m_z = z;
    }
}
