import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.apache.xalan.extensions.XPathFunctionResolverImpl;
import org.apache.xalan.extensions.ExtensionNamespaceContext;
import org.xml.sax.InputSource;

/**
 * JAXP 1.3 XPath API sample.
 * 
 * This sample shows how to use the sample XPathFunctionResolver to evaluate
 * XPath expressions containing Java or EXSLT extension functions. 
 * 
 * To support extension functions in XPath expressions, you have to set an 
 * XPathFunctionResolver on the XPath object. You can provide your implementation
 * of XPathFunctionResolver or use sample XPathFunctionResolver in
 * org.apache.xalan.extensions.XPathFunctionResolverImpl, which supports
 * the usage of Java and EXSLT extension functions.
 * 
 * You also need to provide a NamespaceContext for the XPath evaluation. You 
 * can provide your own implementation of NamespaceContext, or use the sample
 * implementation in org.apache.xalan.extensions.ExtensionNamespaceContext, 
 * which supports the following namespace prefix to URI mappings:
 * 
 * 	java  --> http://xml.apache.org/xalan/java
 * 	exslt --> http://exslt.org/common
 * 	math  --> http://exslt.org/math
 * 	set   --> http://exslt.org/sets
 * 	str   --> http://exslt.org/strings
 * 	dyn   --> http://exslt.org/dynamic
 * 	datetime --> http://exslt.org/dates-and-times
 * 
 * To run this sample, you have to put the directory containing ExtensionTest.class
 * on your classpath.
 */
public class ExtensionFunctionResolver {

    public static final String EXPR1 = "math:max(/doc/num)";

    public static final String EXPR2 = "java:ExtensionTest.test('Bob')";

    public static void main(String[] args) throws Exception {
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        xpath.setNamespaceContext(new ExtensionNamespaceContext());
        xpath.setXPathFunctionResolver(new XPathFunctionResolverImpl());
        Object result = null;
        InputSource context = new InputSource("numlist.xml");
        result = xpath.evaluate(EXPR1, context, XPathConstants.NUMBER);
        System.out.println(EXPR1 + " = " + result);
        result = xpath.evaluate(EXPR2, context, XPathConstants.STRING);
        System.out.println(EXPR2 + " = " + result);
    }
}
