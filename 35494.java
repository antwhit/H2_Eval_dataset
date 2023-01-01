import java.io.*;
import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;

/**
 * <p><code>SAXBuilderDemo</code> demonstrates how to
 *   build a JDOM <code>Document</code> using a SAX 2.0
 *   parser.
 * </p>
 * 
 * @author Brett McLaughlin
 * @author Jason Hunter
 * @version 1.0
 */
public class SAXBuilderDemo {

    /**
     * <p>
     * This provides a static entry point for creating a JDOM
     *   <code>{@link Document}</code> object using a SAX 2.0
     *   parser (an <code>XMLReader</code> implementation).
     * </p>
     *
     * @param args <code>String[]</code>
     *        <ul>
     *         <li>First argument: filename of XML document to parse</li>
     *         <li>Second argument: optional boolean on whether to expand
     *         entities</li>
     *         <li>Third argument: optional String name of a SAX Driver class
     *         to use</li>
     *        </ul>
     */
    public static void main(String[] args) {
        if ((args.length < 1) || (args.length > 3)) {
            System.out.println("Usage: java SAXBuilderDemo " + "[XML document filename] ([expandEntities] [SAX Driver Class])");
            return;
        }
        boolean expandEntities = true;
        String filename = args[0];
        String saxDriverClass = null;
        if (args.length > 1) {
            if (args[1].equalsIgnoreCase("false")) {
                expandEntities = false;
            }
            if (args.length > 2) {
                saxDriverClass = args[2];
            }
        }
        try {
            SAXBuilder builder = null;
            if (saxDriverClass == null) {
                builder = new SAXBuilder();
            } else {
                builder = new SAXBuilder(saxDriverClass);
            }
            builder.setExpandEntities(expandEntities);
            Document doc = builder.build(filename);
            XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
            outputter.output(doc, System.out);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
