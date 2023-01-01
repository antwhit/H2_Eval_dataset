import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ event.PackageTest.class, audio.PackageTest.class, filter.PackageTest.class, gui.PackageTest.class })
public class AllTest {
}
