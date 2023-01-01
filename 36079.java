import net.sourceforge.streamdom.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.w3c.dom.*;

class test_DOMStream implements ElementHandler {

    public void handleElement(Element e) {
        String text = "";
        if (e.getFirstChild() != null) text = e.getFirstChild().getNodeValue();
        System.out.println("Element: " + e.getNodeName() + ": " + text);
    }

    public static void main(String[] argv) {
        if (argv.length != 1) {
            System.err.println("java test_DOMStream filename");
            System.exit(-1);
        }
        try {
            DOMStream streamer = new DOMStream();
            SAXErrorHandler logger = new SAXErrorHandler();
            streamer.ignoreElement("codeindex");
            streamer.addElementHandler(new test_DOMStream());
            XMLReader parser = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
            parser.setContentHandler(streamer);
            parser.setErrorHandler(logger);
            parser.parse(argv[0]);
        } catch (SAXException e) {
            System.err.println(e);
        } catch (ParserConfigurationException e) {
            System.err.println(e);
        } catch (java.io.IOException e) {
            System.err.println(e);
        }
    }
}
