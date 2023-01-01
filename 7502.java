import java.util.*;

/**
 * @author Massimo Bartoletti
 * @version 1.0
 */
public class SlowAnySegmentIntersectionDemo extends AnySegmentIntersectionDemo {

    private ResourceBundle bundle;

    public SlowAnySegmentIntersectionDemo(CGTutorial tutorial) {
        super(tutorial, "SlowAnySegmentIntersection");
    }

    public String getString(String key) {
        String value = null;
        if (bundle == null) bundle = ResourceBundle.getBundle("resources.SlowAnySegmentIntersection");
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
        SlowAnySegmentIntersectionDemo demo = new SlowAnySegmentIntersectionDemo(null);
        demo.mainImpl("Any Segment Intersection: Slow Method");
    }
}
