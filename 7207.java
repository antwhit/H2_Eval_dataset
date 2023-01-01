import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;
import org.jdom.Document;
import org.jdom.input.SAXBuilder;

/**
 * Example to show basic XML handling in a template.
 *
 * @author <a href="mailto:geirm@optonline.net">Geir Magnusson Jr.</a>
 * @version $Id: XMLTest.java 718358 2008-11-17 20:18:09Z nbubna $
 */
public class XMLTest {

    public XMLTest(String templateFile) {
        Writer writer = null;
        try {
            Velocity.init();
            SAXBuilder builder;
            Document root = null;
            try {
                builder = new SAXBuilder();
                root = builder.build("test.xml");
            } catch (Exception ee) {
                System.out.println("Exception building Document : " + ee);
                return;
            }
            VelocityContext context = new VelocityContext();
            context.put("root", root);
            Template template = Velocity.getTemplate(templateFile);
            writer = new BufferedWriter(new OutputStreamWriter(System.out));
            template.merge(context, writer);
        } catch (Exception e) {
            System.out.println("Exception : " + e);
        } finally {
            if (writer != null) {
                try {
                    writer.flush();
                    writer.close();
                } catch (Exception ee) {
                    System.out.println("Exception : " + ee);
                }
            }
        }
    }

    public static void main(String[] args) {
        XMLTest t;
        if (args.length < 1) {
            System.out.println("Usage : java XMLTest <templatename>");
            return;
        }
        t = new XMLTest(args[0]);
    }
}
