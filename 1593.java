import java.io.PrintWriter;
import java.io.StringWriter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.ErrorListener;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * @author Morten Jorgensen
 */
public class TransformBean implements SessionBean {

    private SessionContext m_context = null;

    private static final String nullErrorMsg = "<h1>XSL transformation error</h1>" + "<p>'null' parameters sent to the XSL transformation bean's " + "<tt>transform(String document, String translet)</tt> method.</p>";

    private static final String NAMESPACE_FEATURE = "http://xml.org/sax/features/namespaces";

    /**
     * Generates HTML from a basic error message and an exception
     */
    private void errorMsg(PrintWriter out, Exception e, String msg) {
        out.println("<h1>Error</h1>");
        out.println("<p>" + msg + "</p><br>");
        out.println(e.toString());
    }

    /**
     * Main bean entry point
     */
    public String transform(String document, String transletName) {
        final StringWriter sout = new StringWriter();
        final PrintWriter out = new PrintWriter(sout);
        try {
            if ((document == null) || (transletName == null)) {
                out.println(nullErrorMsg);
            } else {
                TransformerFactory tf = TransformerFactory.newInstance();
                try {
                    tf.setAttribute("use-classpath", Boolean.TRUE);
                } catch (IllegalArgumentException iae) {
                    System.err.println("Could not set XSLTC-specific TransformerFactory " + "attributes.  Transformation failed.");
                }
                Transformer t = tf.newTransformer(new StreamSource(transletName));
                final long start = System.currentTimeMillis();
                t.transform(new StreamSource(document), new StreamResult(out));
                final long done = System.currentTimeMillis() - start;
                out.println("<!-- transformed by XSLTC in " + done + "msecs -->");
            }
        } catch (Exception e) {
            errorMsg(out, e, "Impossible state reached.");
        }
        out.close();
        return sout.toString();
    }

    /**
     *
     */
    public void setSessionContext(SessionContext context) {
        m_context = context;
    }

    public void ejbCreate() {
    }

    public void ejbRemove() {
    }

    public void ejbActivate() {
    }

    public void ejbPassivate() {
    }

    public void ejbLoad() {
    }

    public void ejbStore() {
    }
}
