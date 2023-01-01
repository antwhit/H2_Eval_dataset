import java.util.*;

/**
 * @author Massimo Bartoletti
 * @version 1.0
 */
public class FastPointInConvexPolygonDemo extends PointInConvexPolygonDemo {

    private ResourceBundle bundle;

    public FastPointInConvexPolygonDemo(CGTutorial tutorial) {
        super(tutorial, "FastPointInConvexPolygon");
    }

    public String getString(String key) {
        String value = null;
        if (bundle == null) bundle = ResourceBundle.getBundle("resources.FastPointInConvexPolygon");
        try {
            value = bundle.getString(key);
        } catch (MissingResourceException ex) {
        }
        if (value == null) value = super.getString(key);
        return value;
    }

    /**
     * The main() method allows us to run as a standalone demo.
     */
    public static void main(String[] args) {
        FastPointInConvexPolygonDemo demo = new FastPointInConvexPolygonDemo(null);
        demo.mainImpl("Fast Point In Convex Polygon Demo");
    }
}
