import java.io.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

public class xslt {

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println("Usage: java xslt style.xsl < input.xml  > output.html ");
            System.err.println("or: java xslt style.xsl input.xml output.html ");
            System.exit(1);
        }
        if (args.length >= 3) {
            new xslt().transform(args[0], args[1], args[2]);
        } else {
            new xslt().transform(args[0]);
        }
    }

    public xslt() {
    }

    private void transform(String style, String xml, String output) throws TransformerException {
        Source xslSource = new StreamSource(style);
        Source xmlSource = new StreamSource(xml);
        Result result = new StreamResult(output);
        transform(xslSource, xmlSource, result);
    }

    private void transform(String style) throws TransformerException {
        Source xmlSource = new StreamSource(System.in);
        xmlSource.setSystemId("file:.");
        Source xslSource = new StreamSource(style);
        Result result = new StreamResult(System.out);
        transform(xslSource, xmlSource, result);
    }

    public void transform(Source xslSource, Source xmlSource, Result output) throws TransformerException {
        Transformer tx = TransformerFactory.newInstance().newTransformer(xslSource);
        tx.transform(xmlSource, output);
    }
}
