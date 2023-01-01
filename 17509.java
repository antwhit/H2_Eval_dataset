import j2meunit.framework.Test;
import j2meunit.framework.TestCase;
import j2meunit.framework.TestSuite;
import j2meunit.tests.TestTest;
import j2meunit.tests.TestTestCase;

/********************************************************************
 * TestSuite that runs all tests for J2MEUnit.
 */
public class AllTests extends TestCase {

    /***************************************
	 * Creates a new AllTests object.
	 */
    public AllTests() {
    }

    /***************************************
	 * Creates a test suite containing all J2MEUnit tests. 
	 *
	 * @return A new test suite
	 */
    public Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(new TestTest().suite());
        suite.addTest(new TestTestCase().suite());
        return suite;
    }
}
