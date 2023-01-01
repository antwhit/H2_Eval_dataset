import org.makagiga.test.Test;
import org.makagiga.test.Tester;

/**
 * HINT: Run "ant test" to compile and run tests.
 */
public class TestDefault {

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
        String bar = foobar.substring(3);
        assert bar.equals("bar");
    }
}
