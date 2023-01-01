import com.conicsoft.bdkJ.core.Uti;
import com.conicsoft.bdkJ.parser.IXmlDocument;

public class TestXmlAccess extends junit.framework.TestCase {

    private final String path_in;

    private final String path_out;

    private IXmlDocument p_xmldoc = null;

    public TestXmlAccess() {
        super();
        setName("TestXmlAccess");
        path_in = Uti.getBdkDevTestDir() + "testworkflow_dialog.stem";
        path_out = path_in + ".out";
        if (p_xmldoc == null) p_xmldoc = com.conicsoft.bdkJ.parser.Uti.CreateXml();
    }

    public void testWrite() {
        p_xmldoc.open(path_in);
        p_xmldoc.save(path_out);
    }
}
