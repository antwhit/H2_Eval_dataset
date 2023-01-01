import org.jostraca.util.FileUtil;
import org.jostraca.util.PropertySet;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import java.io.PrintWriter;
import java.io.File;

/** <b>Description:</b><br>
 *  Test cases for BasicRubyFormat
 */
public class BasicRubyFormatTest extends BasicFormatTestSupport {

    public static final boolean RUN_ONLY_COMMENTED_OUT = false;

    public static final String RUBY_FOLDER = "ruby";

    public static final String RUBY_TEMPLATE_SUFFIX = "_rb.jtm";

    public BasicRubyFormatTest(String rName) {
        super(rName);
    }

    public static TestSuite suite() {
        return new TestSuite(BasicRubyFormatTest.class);
    }

    public static void main(String[] rArgs) {
        handleArgs(rArgs);
        TestRunner.run(suite());
    }

    public String getTestFolder() {
        return RUBY_FOLDER;
    }

    public String getJostracaCodeWriterFileName() {
        return "Writer.rb";
    }

    public void testMethodInsert() throws Exception {
        if (RUN_ONLY_COMMENTED_OUT) {
            return;
        }
        generateTemplate(TEST_MethodInsert);
        assertTrue(validateMethodInsert());
        PropertySet testPS = getTestPS();
        PropertySet refPS = getReferencePS();
        refPS.clear();
        refPS.set("boolean", "false");
        assertTrue(testPS.contains(refPS));
    }

    protected String makeCMDForTemplate(String rTemplateNameSansSuffix, String rExtraJostracaArgs, String rCodeWriterArgs, boolean rSetOutputFolder) {
        String codeWriterArgs = "";
        if (0 < rCodeWriterArgs.length()) {
            codeWriterArgs = rCodeWriterArgs;
        }
        String templateName = PREFIX_TEST + rTemplateNameSansSuffix + RUBY_TEMPLATE_SUFFIX;
        File templateFilePath = new File(RUBY_FOLDER, templateName);
        String setOutputFolder = "";
        if (rSetOutputFolder) {
            setOutputFolder = " " + "-o " + getTestFolder();
        }
        String cmd = getJostracaCmd() + " " + "-gB " + setOutputFolder + " " + rExtraJostracaArgs + " " + templateFilePath.getPath() + " " + codeWriterArgs;
        return cmd;
    }
}
