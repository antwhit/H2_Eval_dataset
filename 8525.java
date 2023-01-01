/**
 * @test
 * @bug 0000000
 * @summary Determine if proper warning messages are printed when know.
 * @author jamieh
 * @library ../lib/
 * @build JavadocTester
 * @build TestTagMisuse
 * @run main TestTagMisuse
 */
public class TestTagMisuse extends JavadocTester {

    private static final String BUG_ID = "no-bug-id";

    private static final String[][] TEST = { { WARNING_OUTPUT, "warning - Tag @param cannot be used in field documentation." }, { WARNING_OUTPUT, "warning - Tag @throws cannot be used in field documentation." }, { WARNING_OUTPUT, "warning - Tag @return cannot be used in constructor documentation." }, { WARNING_OUTPUT, "warning - Tag @throws cannot be used in inline documentation." } };

    private static final String[][] NEGATED_TEST = NO_TEST;

    private static final String[] ARGS = new String[] { "-d", BUG_ID, SRC_DIR + FS + "TestTagMisuse.java" };

    /**
     * The entry point of the test.
     * @param args the array of command line arguments.
     */
    public static void main(String[] args) {
        TestTagMisuse tester = new TestTagMisuse();
        run(tester, ARGS, TEST, NEGATED_TEST);
        tester.printSummary();
    }

    /**
     * {@inheritDoc}
     */
    public String getBugId() {
        return BUG_ID;
    }

    /**
     * {@inheritDoc}
     */
    public String getBugName() {
        return getClass().getName();
    }

    /**
     * {@throws blah}
     * Here is a bad field tag:
     * @throws foo
     * @param foo.
     */
    public int field;

    /**
     * Here is a bad constructor tag:
     * @return blah
     */
    public TestTagMisuse() {
    }
}
