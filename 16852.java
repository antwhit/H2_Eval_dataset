import java.io.*;
import java.util.*;
import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;
import org.jdom.xpath.*;

/**
 * <p><code>XPathReader</code> demonstrates how to
 *   read a Servlet 2.2 Web Archive file using XPath.
 * </p>
 * 
 * @author Jason Hunter
 * @version 1.0
 */
public class XPathReader {

    public static void main(String[] args) throws IOException, JDOMException {
        if (args.length != 1) {
            System.err.println("Usage: java XPathReader [web.xml]");
            return;
        }
        String filename = args[0];
        PrintStream out = System.out;
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(new File(filename));
        XPath servletPath = XPath.newInstance("//servlet");
        List servlets = servletPath.selectNodes(doc);
        out.println("This WAR has " + servlets.size() + " registered servlets:");
        Iterator i = servlets.iterator();
        while (i.hasNext()) {
            Element servlet = (Element) i.next();
            out.print("\t" + servlet.getChild("servlet-name").getTextTrim() + " for " + servlet.getChild("servlet-class").getTextTrim());
            List initParams = servlet.getChildren("init-param");
            out.println(" (it has " + initParams.size() + " init params)");
        }
        XPath rolePath = XPath.newInstance("//security-role/role-name/text()");
        List roleNames = rolePath.selectNodes(doc);
        if (roleNames.size() == 0) {
            out.println("This WAR contains no roles");
        } else {
            out.println("This WAR contains " + roleNames.size() + " roles:");
            i = roleNames.iterator();
            while (i.hasNext()) {
                out.println("\t" + ((Text) i.next()).getTextTrim());
            }
        }
    }
}
