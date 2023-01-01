public class TestOverridenPrivateMethods extends JavadocTester {

    private static final String BUG_ID = "4634891";

    private static final String[][] TEST = { { BUG_ID + FS + "pkg1" + FS + "SubClass.html", "<dt><strong>Overrides:</strong></dt>" + NL + "<dd><code><a href=\"../pkg1/BaseClass.html#publicMethod" }, { BUG_ID + FS + "pkg2" + FS + "SubClass.html", "<dt><strong>Overrides:</strong></dt>" + NL + "<dd><code><a href=\"../pkg1/BaseClass.html#publicMethod" } };

    private static final String[][] NEGATED_TEST = { { BUG_ID + FS + "pkg1" + FS + "SubClass.html", "<dt><strong>Overrides:</strong></dt>" + NL + "<dd><code><a href=\"../pkg1/BaseClass.html#packagePrivateMethod" }, { BUG_ID + FS + "pkg1" + FS + "SubClass.html", "<dt><strong>Overrides:</strong></dt>" + NL + "<dd><code><a href=\"../pkg1/BaseClass.html#privateMethod" }, { BUG_ID + FS + "pkg2" + FS + "SubClass.html", "<dt><strong>Overrides:</strong></dt>" + NL + "<dd><code><a href=\"../pkg1/BaseClass.html#privateMethod" }, { BUG_ID + FS + "pkg2" + FS + "SubClass.html", "Overrides:</strong></dt><dd><code><a href=\"../pkg1/BaseClass.html#packagePrivateMethod" } };

    private static final String[] ARGS = new String[] { "-d", BUG_ID, "-sourcepath", SRC_DIR, "pkg1", "pkg2" };

    /**
     * The entry point of the test.
     * @param args the array of command line arguments.
     */
    public static void main(String[] args) {
        TestOverridenPrivateMethods tester = new TestOverridenPrivateMethods();
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
