import java.util.*;

/**
 * @author Massimo Bartoletti
 * @version 1.0
 */
public class SweepingLineAnySegmentIntersectionDemo extends AnySegmentIntersectionDemo {

    private ResourceBundle bundle;

    public SweepingLineAnySegmentIntersectionDemo(CGTutorial tutorial) {
        super(tutorial, "SweepingLineAnySegmentIntersection");
    }

    public String getString(String key) {
        String value = null;
        if (bundle == null) bundle = ResourceBundle.getBundle("resources.SweepingLineAnySegmentIntersection");
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
        SweepingLineAnySegmentIntersectionDemo demo = new SweepingLineAnySegmentIntersectionDemo(null);
        demo.mainImpl("Any Segment Intersection: Sweeping Line Method");
    }
}
