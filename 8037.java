import java.util.*;

/**
 * @author Massimo Bartoletti
 * @version 1.0
 */
public class TwoSegmentIntersectionDemo extends CGDemoModule {

    private ResourceBundle bundle;

    public TwoSegmentIntersectionDemo(CGTutorial tutorial) {
        super(tutorial, "TwoSegmentIntersection");
    }

    public String getString(String key) {
        String value = null;
        if (bundle == null) bundle = ResourceBundle.getBundle("resources.TwoSegmentIntersection");
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
        TwoSegmentIntersectionDemo demo = new TwoSegmentIntersectionDemo(null);
        demo.mainImpl("2-Segment Intersection Demo");
    }
}
