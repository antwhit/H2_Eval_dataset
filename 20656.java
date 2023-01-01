public class TestDocRootInlineTag extends JavadocTester {

    private static final String BUG_ID = "4369014-4851991";

    private static final String[][] TEST = { { BUG_ID + FS + "TestDocRootTag.html", "<A HREF=\"http://www.java.sun.com/j2se/1.4/docs/api/java/io/File.html?is-external=true\" " + "title=\"class or interface in java.io\"><CODE>File</CODE></A>" }, { BUG_ID + FS + "TestDocRootTag.html", "<a href=\"./glossary.html\">glossary</a>" }, { BUG_ID + FS + "TestDocRootTag.html", "<A HREF=\"http://www.java.sun.com/j2se/1.4/docs/api/java/io/File.html?is-external=true\" " + "title=\"class or interface in java.io\"><CODE>Second File Link</CODE></A>" }, { BUG_ID + FS + "TestDocRootTag.html", "The value of @docRoot is \"./\"" }, { BUG_ID + FS + "index-all.html", "My package page is " + "<a href=\"./pkg/package-summary.html\">here</a>" } };

    private static final String[][] NEGATED_TEST = NO_TEST;

    private static final String[] ARGS = new String[] { "-bottom", "The value of @docRoot is \"{@docRoot}\"", "-d", BUG_ID, "-sourcepath", SRC_DIR, "-linkoffline", "http://www.java.sun.com/j2se/1.4/docs/api", SRC_DIR, SRC_DIR + FS + "TestDocRootTag.java", "pkg" };

    /**
     * The entry point of the test.
     * @param args the array of command line arguments.
     */
    public static void main(String[] args) {
        TestDocRootInlineTag tester = new TestDocRootInlineTag();
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
