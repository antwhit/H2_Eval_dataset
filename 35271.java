import org.jcvi.AllInternalUnitTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Runs all external and internal unit tests.
 * @author dkatzel
 *
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ org.jcvi.AllUnitTests.class, AllInternalUnitTests.class })
public class AllUnitTests {
}
