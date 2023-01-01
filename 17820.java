import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.w3c.dom.Document;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class BatchImporter {

    private Document document;

    private String outputfile;

    public BatchImporter(InputStream is) throws SAXException, ParserConfigurationException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.parse(is);
        outputfile = document.getDocumentElement().getAttribute("destinationfile");
        NodeList nodes = document.getElementsByTagName("ananovaconfig");
        int n = nodes.getLength();
        if (n > 0) {
            AnanovaConfig ac = AnanovaConfig.getInstance();
            for (int i = 0; i < n; i++) {
                Element el = (Element) nodes.item(i);
                ac.configure(el);
            }
        }
    }

    public void run() throws TVException, Exception {
        AnanovaConfig ac = AnanovaConfig.getInstance();
        AnanovaImporter ai = new AnanovaImporter();
        ai.setOutput(new FileOutputStream(outputfile));
        NodeList listings = document.getElementsByTagName("listing");
        int listingsCount = listings.getLength();
        for (int i = 0; i < listingsCount; i++) {
            Element listing = (Element) listings.item(i);
            NodeList sourceNodeList = listing.getElementsByTagName("source");
            Element source = (Element) sourceNodeList.item(0);
            String type = source.getAttribute("type");
            String uri = source.getAttribute("uri");
            if ("ananova".equals(type)) {
                URL listingsUrl = new URL(ac.getBaseUri() + uri);
                ai.parseStream(listingsUrl.openStream());
            } else {
                throw new TVException("Type: " + type + " not yet implemented");
            }
        }
        ai.writeOutput();
    }

    public static void main(String[] args) {
        try {
            if (args.length < 1) {
                System.out.println("Usage: java BatchImporter configfile");
                System.exit(1);
            }
            String filename = args[0];
            InputStream is = new FileInputStream(filename);
            BatchImporter importer = new BatchImporter(is);
            importer.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
