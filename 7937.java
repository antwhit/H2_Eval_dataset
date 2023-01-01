public class TestDupThrowsTags extends JavadocTester {

    private static final String BUG_ID = "4525364";

    private static final String[] ARGS = new String[] { "-d", BUG_ID, SRC_DIR + FS + "TestDupThrowsTags.java" };

    /**
     * The entry point of the test.
     * @param args the array of command line arguments.
     */
    public static void main(String[] args) {
        String[][] tests = new String[4][2];
        for (int i = 0; i < tests.length; i++) {
            tests[i][0] = BUG_ID + FS + "TestDupThrowsTags.html";
            tests[i][1] = "Test " + (i + 1) + " passes";
        }
        TestDupThrowsTags tester = new TestDupThrowsTags();
        run(tester, ARGS, tests, NO_TEST);
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
     * @throws java.io.IOException Test 1 passes
     * @throws java.io.IOException Test 2 passes
     * @throws java.lang.NullPointerException Test 3 passes
     * @throws java.io.IOException Test 4 passes
     */
    public void method() {
    }
}
