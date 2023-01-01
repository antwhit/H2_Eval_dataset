/**
 * @test
 * @bug 4530727
 * @summary When an exception is declared in the method signature but
 * not documented with a throws tag, we generate a link to it in the
 * throws section.  Make sure that the link is below a Throws heading.
 * @author jamieh
 * @library ../lib/
 * @build JavadocTester
 * @build TestThrowsHead
 * @run main TestThrowsHead
 */
public class TestThrowsHead extends JavadocTester {

    private static final String BUG_ID = "4530727";

    private static final String[][] TEST = { { BUG_ID + FS + "C.html", "<dt><span class=\"strong\">Throws:</span>" } };

    private static final String[][] NEGATED_TEST = NO_TEST;

    private static final String[] ARGS = new String[] { "-d", BUG_ID, SRC_DIR + FS + "C.java" };

    /**
     * The entry point of the test.
     * @param args the array of command line arguments.
     */
    public static void main(String[] args) {
        TestThrowsHead tester = new TestThrowsHead();
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
}
