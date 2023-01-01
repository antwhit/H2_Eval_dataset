import junit.framework.*;

/**
 *
 * @author Administrateur
 */
public class SugarTestSuite {

    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(SugarTest.class);
        return suite;
    }

    /**
     * Runs the test suite using the textual runner.
     */
    public static void main(String[] args) {
        junit.textui.TestRunner.runAndWait(suite());
    }
}
