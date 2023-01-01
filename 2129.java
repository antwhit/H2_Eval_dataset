import java.io.BufferedReader;
import java.io.StringReader;
import org.deft.repository.ast.DeftASTFrame;
import org.deft.repository.ast.TreeNode;
import org.deft.repository.ast.serialize.ParenTreeNodeSerializer;
import org.deft.xml.Factory;
import org.deft.xml.TestXMLTree;
import xtc.parser.ParseError;
import xtc.parser.Result;

public class commentTreeTest {

    /**
	 * @param args
	 */
    public static void main(String[] args) throws Exception {
        new commentTreeTest().run();
    }

    public void run() throws Exception {
        TestXMLTree parser = setupParserForString("<!-- comment -->");
        Result result = parser.pComment(0);
        if (result.hasValue()) {
            System.out.println("parsing successful");
            TreeNode root = (TreeNode) result.semanticValue();
            System.out.println(root.getChildCount());
            DeftASTFrame frame = new DeftASTFrame((TreeNode) result.semanticValue());
            frame.setVisible(true);
            frame.setSize(400, 800);
            ParenTreeNodeSerializer serializer = new ParenTreeNodeSerializer();
            System.out.println(serializer.convertTreeToString(root));
        } else {
            System.out.println("Error: " + result.parseError().msg);
        }
    }

    public boolean errorOccurred(Result result) {
        return result.parseError() != ParseError.DUMMY;
    }

    private TestXMLTree setupParserForString(String toParse) {
        StringReader reader = new StringReader(toParse);
        TestXMLTree parser = new TestXMLTree(reader, "dummy");
        Factory f = Factory.instance();
        f.setDataToParse(new BufferedReader(new StringReader(toParse)));
        return parser;
    }
}
