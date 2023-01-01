import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * TestSuite that is composed of the individual test classes.  Pick up
 * all the individual PCGen test suites into this one.
 *
 * @author B. K. Oxley (binkley) <binkley@alumni.rice.edu>
 * @version $Revision: 201 $
 * @see <a href="http://www-106.ibm.com/developerworks/library/j-ant/?dwzone=java">Incremental development with Ant and JUnit</a>
 */
public class AllJUnitTests extends TestCase {

    /**
	 * Constructor
	 * @param name
	 */
    public AllJUnitTests(String name) {
        super(name);
    }

    /**
	 * main
	 * @param args
	 */
    public static void main(String[] args) {
        junit.textui.TestRunner.run(AllJUnitTests.suite());
    }

    /**
	 * suite
	 * @return Test
	 */
    public static Test suite() {
        TestSuite suite = new TestSuite("All PCGEN Tests");
        suite.addTest(pcgen.core.AllJUnitTests.suite());
        return suite;
    }
}
