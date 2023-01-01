import java.util.*;

/**
 * @author Massimo Bartoletti
 * @version 1.1
 */
public class SlowRayCrossSegmentSetDemo extends RayCrossSegmentSetDemo {

    private ResourceBundle bundle;

    public SlowRayCrossSegmentSetDemo(CGTutorial tutorial) {
        super(tutorial, "SlowRayCrossSegmentSet");
    }

    public String getString(String key) {
        String value = null;
        if (bundle == null) bundle = ResourceBundle.getBundle("resources.SlowRayCrossSegmentSet");
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
        SlowRayCrossSegmentSetDemo demo = new SlowRayCrossSegmentSetDemo(null);
        demo.mainImpl("Slow Ray Cross Segment Set Demo");
    }
}
