public class TestNoPackagesFile extends JavadocTester {

    private static final String BUG_ID = "4475679";

    private static final String[] ARGS = new String[] { "-d", BUG_ID, "-sourcepath", SRC_DIR, SRC_DIR + FS + "C.java" };

    /**
     * The entry point of the test.
     * @param args the array of command line arguments.
     */
    public static void main(String[] args) {
        TestNoPackagesFile tester = new TestNoPackagesFile();
        run(tester, ARGS, NO_TEST, NO_TEST);
        if ((new java.io.File(BUG_ID + FS + "packages.html")).exists()) {
            throw new Error("Test Fails: packages file should not be " + "generated anymore.");
        } else {
            System.out.println("Test passes:  packages.html not found.");
        }
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
