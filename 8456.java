import junit.framework.*;
import junit.framework.TestCase;

public class RootSuite extends TestCase {

    public RootSuite(String testName) {
        super(testName);
    }

    public static junit.framework.Test suite() {
        junit.framework.TestSuite suite = new junit.framework.TestSuite("RootSuite");
        suite.addTest(com.ComSuite.suite());
        return suite;
    }

    protected void setUp() throws java.lang.Exception {
    }

    protected void tearDown() throws java.lang.Exception {
    }
}
