public class TestBackSlashInLink extends JavadocTester {

    private static final String BUG_ID = "4511110";

    private static final String[][] TEST = { { BUG_ID + FS + "C.html", "src-html/C.html#line.7" } };

    private static final String[][] NEGATED_TEST = NO_TEST;

    private static final String[] ARGS = new String[] { "-d", BUG_ID, "-sourcepath", SRC_DIR, "-linksource", SRC_DIR + FS + "C.java" };

    /**
     * The entry point of the test.
     * @param args the array of command line arguments.
     */
    public static void main(String[] args) {
        TestBackSlashInLink tester = new TestBackSlashInLink();
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
