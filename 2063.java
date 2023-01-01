import java.io.*;
import org.kxml2.io.*;
import org.xmlpull.v1.*;

public class Roundtrip {

    XmlPullParser parser;

    XmlSerializer serializer;

    public Roundtrip(XmlPullParser parser, XmlSerializer serializer) {
        this.parser = parser;
        this.serializer = serializer;
    }

    public void writeStartTag() throws XmlPullParserException, IOException {
        if (!parser.getFeature(parser.FEATURE_REPORT_NAMESPACE_ATTRIBUTES)) {
            for (int i = parser.getNamespaceCount(parser.getDepth() - 1); i < parser.getNamespaceCount(parser.getDepth()) - 1; i++) {
                serializer.setPrefix(parser.getNamespacePrefix(i), parser.getNamespaceUri(i));
            }
        }
        serializer.startTag(parser.getNamespace(), parser.getName());
        for (int i = 0; i < parser.getAttributeCount(); i++) {
            serializer.attribute(parser.getAttributeNamespace(i), parser.getAttributeName(i), parser.getAttributeValue(i));
        }
    }

    public void writeToken() throws XmlPullParserException, IOException {
        switch(parser.getEventType()) {
            case XmlPullParser.START_DOCUMENT:
                break;
            case XmlPullParser.END_DOCUMENT:
                serializer.endDocument();
                break;
            case XmlPullParser.START_TAG:
                writeStartTag();
                break;
            case XmlPullParser.END_TAG:
                serializer.endTag(parser.getNamespace(), parser.getName());
                break;
            case XmlPullParser.IGNORABLE_WHITESPACE:
                serializer.ignorableWhitespace(parser.getText());
                break;
            case XmlPullParser.TEXT:
                serializer.text(parser.getText());
                break;
            case XmlPullParser.ENTITY_REF:
                serializer.entityRef(parser.getName());
                break;
            case XmlPullParser.CDSECT:
                serializer.cdsect(parser.getText());
                break;
            case XmlPullParser.PROCESSING_INSTRUCTION:
                serializer.processingInstruction(parser.getText());
                break;
            case XmlPullParser.COMMENT:
                serializer.comment(parser.getText());
                break;
            case XmlPullParser.DOCDECL:
                serializer.docdecl(parser.getText());
                break;
            default:
                throw new RuntimeException("unrecognized event: " + parser.getEventType());
        }
    }

    public void roundTrip() throws XmlPullParserException, IOException {
        while (parser.getEventType() != parser.END_DOCUMENT) {
            writeToken();
            parser.nextToken();
        }
        writeToken();
    }

    public static void main(String[] args) throws Exception {
        if (args.length == 0) throw new RuntimeException("input xml file name expected");
        for (int i = 0; i < args.length; i++) {
            System.out.println("processing: " + args[i]);
            XmlPullParser pp = new KXmlParser();
            pp.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, true);
            XmlSerializer serializer = new KXmlSerializer();
            pp.setInput(new FileReader(args[i]));
            serializer.setOutput(System.out, null);
            (new Roundtrip(pp, serializer)).roundTrip();
            serializer.flush();
        }
    }
}
