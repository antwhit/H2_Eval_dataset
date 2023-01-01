import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

/**
 * <p><code>WarReader</code> demonstrates how to
 *   read a Servlet 2.2 Web Archive file with JDOM.
 * </p>
 * 
 * @author Brett McLaughlin, Jason Hunter
 * @version 1.0
 */
public class WarReader {

    public static void main(String[] args) throws IOException, JDOMException {
        if (args.length != 1) {
            System.err.println("Usage: java WarReader [web.xml]");
            return;
        }
        String filename = args[0];
        PrintStream out = System.out;
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(new File(filename));
        Element root = doc.getRootElement();
        List servlets = root.getChildren("servlet");
        out.println("This WAR has " + servlets.size() + " registered servlets:");
        Iterator i = servlets.iterator();
        while (i.hasNext()) {
            Element servlet = (Element) i.next();
            out.print("\t" + servlet.getChild("servlet-name").getTextTrim() + " for " + servlet.getChild("servlet-class").getTextTrim());
            List initParams = servlet.getChildren("init-param");
            out.println(" (it has " + initParams.size() + " init params)");
        }
        List securityRoles = root.getChildren("security-role");
        if (securityRoles.size() == 0) {
            out.println("This WAR contains no roles");
        } else {
            Element securityRole = (Element) securityRoles.get(0);
            List roleNames = securityRole.getChildren("role-name");
            out.println("This WAR contains " + roleNames.size() + " roles:");
            i = roleNames.iterator();
            while (i.hasNext()) {
                Element e = (Element) i.next();
                out.println("\t" + e.getTextTrim());
            }
        }
        List distrib = root.getChildren("distributed");
        if (distrib.size() == 0) {
            out.println("This WAR is not distributed");
        } else {
            out.println("This WAR is distributed");
        }
    }
}
