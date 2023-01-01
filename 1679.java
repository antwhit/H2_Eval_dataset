import javax.vecmath.*;

class Exchange {

    private static Point3d intersectionPoint;

    public static void setIntersectionPoint(Point3d in) {
        intersectionPoint = in;
    }

    public static Point3d getIntersectionPoint() {
        return intersectionPoint;
    }
}
