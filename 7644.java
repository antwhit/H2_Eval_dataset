import java.io.*;
import java.net.*;
import org.xmlpull.v1.*;

public class XmlPullTest extends AbstractTest {

    private static final boolean USE_SB = true;

    private XmlPullParser xpp;

    private XmlPullParserFactory factory;

    private StringBuffer buf = new StringBuffer();

    private boolean ns;

    private boolean no_reuse = false;

    public static void main(String[] args) throws Exception {
        new XmlPullTest(args);
    }

    public XmlPullTest(String[] args) throws Exception {
        super(args);
        run();
    }

    public void init(String[] options) throws Exception {
        if (options == null || options.length < 1) {
            throw badUsage("at least one option must be present");
        } else {
            if (options[0].equalsIgnoreCase("ns_on")) ns = true; else if (options[0].equalsIgnoreCase("ns_off")) ns = false; else {
                throw badUsage("expected ns_on or ns_off as first option");
            }
        }
        if (options.length >= 2) {
            if (!"no_reuse".equals(options[1])) {
                throw badUsage("second option if present must be 'no_reuse'");
            }
            no_reuse = true;
        }
        factory = XmlPullParserFactory.newInstance(System.getProperty(XmlPullParserFactory.PROPERTY_NAME), null);
        info("using factory " + factory.getClass());
        info("namespaces: " + ns);
        info("reuse parser instances: " + (!no_reuse));
        factory.setNamespaceAware(ns);
        xpp = factory.newPullParser();
        info("using parser " + xpp.getClass());
    }

    public String usage() {
        return super.usage() + " <ns_on|ns_off> [no_reuse]\n";
    }

    int[] holderForStartAndLength = new int[2];

    protected void parse(Reader r) throws Exception {
        if (no_reuse) {
            xpp = factory.newPullParser();
        }
        xpp.setInput(r);
        int token;
        LOOP: while (true) {
            token = xpp.next();
            switch(token) {
                case XmlPullParser.START_TAG:
                    String uri = xpp.getNamespace();
                    String localName = xpp.getName();
                    String prefix = xpp.getPrefix();
                    int len = xpp.getAttributeCount();
                    for (int i = 0; i < len; i++) {
                        String n = xpp.getAttributeName(i);
                        String v = xpp.getAttributeValue(i);
                    }
                    if (USE_SB) buf.setLength(0);
                    break;
                case XmlPullParser.END_TAG:
                    if (USE_SB) {
                        String c = buf.toString();
                    }
                    break;
                case XmlPullParser.TEXT:
                    if (USE_SB) {
                        char[] ch = xpp.getTextCharacters(holderForStartAndLength);
                        int start = holderForStartAndLength[0];
                        int length = holderForStartAndLength[1];
                        buf.append(ch, start, length);
                    } else {
                        String s = xpp.getText();
                    }
                    break;
                case XmlPullParser.END_DOCUMENT:
                    break LOOP;
            }
        }
        r.close();
    }
}
