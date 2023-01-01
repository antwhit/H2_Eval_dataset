import org.jostraca.util.FileUtil;
import org.jostraca.util.PropertySet;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import java.io.PrintWriter;
import java.io.File;

/** 
 *  Test cases for BasicPerlFormat
 */
public class BasicPerlFormatTest extends BasicFormatTestSupport {

    public static final boolean RUN_ONLY_COMMENTED_OUT = false;

    public static final String PERL_FOLDER = "perl";

    public static final String PERL_TEMPLATE_SUFFIX = "_pl.jtm";

    public BasicPerlFormatTest(String pName) {
        super(pName);
    }

    public static TestSuite suite() {
        return new TestSuite(BasicPerlFormatTest.class);
    }

    public static void main(String[] pArgs) {
        handleArgs(pArgs);
        TestRunner.run(suite());
    }

    public String getTestFolder() {
        return PERL_FOLDER;
    }

    public String getJostracaCodeWriterFileName() {
        return "Writer.pl";
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
        refPS.set("boolean", "0");
        assertTrue(testPS.contains(refPS));
    }

    protected String makeCMDForTemplate(String pTemplateNameSansSuffix, String pExtraJostracaArgs, String pCodeWriterArgs, boolean pSetOutputFolder) {
        String codeWriterArgs = "";
        if (0 < pCodeWriterArgs.length()) {
            codeWriterArgs = pCodeWriterArgs;
        }
        String templateName = PREFIX_TEST + pTemplateNameSansSuffix + PERL_TEMPLATE_SUFFIX;
        File templateFilePath = new File(PERL_FOLDER, templateName);
        String setOutputFolder = "";
        if (pSetOutputFolder) {
            setOutputFolder = " " + "-o " + getTestFolder();
        }
        String cmd = getJostracaCmd() + " " + "-gB " + setOutputFolder + " " + pExtraJostracaArgs + " " + templateFilePath.getPath() + " " + codeWriterArgs;
        return cmd;
    }
}
