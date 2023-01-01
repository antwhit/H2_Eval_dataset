import org.xaware.testing.util.BaseTestCase;

/**
 * Test of XAFunctoids. 
 * @author jtarnowski
 */
public class XAFunctoidsTestCase extends BaseTestCase {

    /**
     * 
     */
    public XAFunctoidsTestCase() {
        super("FunctoidTestCase");
    }

    /**
     * @param arg0
     */
    public XAFunctoidsTestCase(String arg0) {
        super(arg0);
    }

    /**
     * Test replaceAll(String oldchar, String newChar, String src) 
     */
    public void testReplaceAll() {
        String orig = "abcdadcba";
        String result;
        result = XAFunctoids.replaceAll("a", "X", orig);
        assertEquals("testReplaceAll", "XbcdXdcbX", result);
        result = XAFunctoids.replaceAll("a", "", orig);
        assertEquals("testReplaceAll", "bcddcb", result);
        result = XAFunctoids.replaceAll("", "X", orig);
        assertEquals("testReplaceAll", "abcdadcba", result);
        result = XAFunctoids.replaceAll("a", "X", "");
        assertEquals("testReplaceAll", "", result);
    }
}
