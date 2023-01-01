import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * $Id: AllTests.java 185 2009-08-17 13:47:24Z jwierum $
 * @author jwierum
 */
public final class AllTests {

    private AllTests() {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite("Test for default package");
        suite.addTest(fitgoodies.FitGoodiesTests.suite());
        return suite;
    }
}
