import org.AllIntegrationTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Runs all unit AND integration tests which will be slow.
 * @author dkatzel
 *
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ AllUnitTests.class, AllIntegrationTests.class })
public class AllTests {
}
