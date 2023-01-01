import org.makagiga.test.AbstractTest;
import org.makagiga.test.Test;
import org.makagiga.test.Tester;

/**
 * HINT: Run "ant test" to compile and run tests.
 */
@Test(className = Void.class)
public class TestDefault extends AbstractTest {

    @Test
    public void test_exceptions() {
        Tester.testException(NullPointerException.class, new Tester.Code() {

            public void run() throws Throwable {
                String nullString = null;
                nullString.length();
            }
        });
    }

    @Test
    public void test_misc() {
        String foobar = "foobar";
        String s = foobar.substring(3);
        assertEquals("bar", s);
    }
}
