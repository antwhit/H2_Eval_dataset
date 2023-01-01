import java.util.*;

/**
 * @author Massimo Bartoletti
 * @version 1.0
 */
public class RayCrossingsDemo extends PointInSimplePolygonDemo {

    private ResourceBundle bundle;

    public RayCrossingsDemo(CGTutorial tutorial) {
        super(tutorial, "RayCrossings");
    }

    public String getString(String key) {
        String value = null;
        if (bundle == null) bundle = ResourceBundle.getBundle("resources.RayCrossings");
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
        RayCrossingsDemo demo = new RayCrossingsDemo(null);
        demo.mainImpl("Ray Crossings Demo");
    }
}
