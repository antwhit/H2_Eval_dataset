public class TestAnnotationTypes extends JavadocTester {

    private static final String BUG_ID = "4973609";

    private static final String[] ARGS = new String[] { "-d", BUG_ID, "-sourcepath", SRC_DIR, "-source", "1.5", "pkg" };

    private static final String[][] TEST = NO_TEST;

    private static final String[][] NEGATED_TEST = { { BUG_ID + FS + "pkg" + FS + "AnnotationType.html", "<HR>" + NL + NL + "<P>" + NL + NL + "<P>" + "<!-- ========= END OF CLASS DATA ========= -->" + "<HR>" } };

    /**
     * The entry point of the test.
     * @param args the array of command line arguments.
     */
    public static void main(String[] args) {
        TestAnnotationTypes tester = new TestAnnotationTypes();
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
