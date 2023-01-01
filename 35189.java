import java.io.*;
import java.util.*;
import java.net.*;
import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class xsltc {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("# xslt translet compiler");
            System.err.println("# usage: xsltc xsl-source");
            System.err.println("#  -source = file/url/string-value");
        } else xsltc(args[0], args);
    }

    static String xslname = "translet1";

    static void xsltc(String xsl, String[] args) {
        try {
            System.setProperty("javax.xml.transform.TransformerFactory", "org.apache.xalan.xsltc.trax.TransformerFactoryImpl");
            StreamSource trsource = getSource(xsl);
            if (args.length > 1) xslname = args[1];
            TransformerFactory factory = TransformerFactory.newInstance();
            factory.setAttribute("generate-translet", Boolean.TRUE);
            factory.setAttribute("translet-name", xslname);
            Templates templates = factory.newTemplates(trsource);
        } catch (Exception ex) {
            System.err.println("# xsltc-Error: " + ex.getMessage() + "\n");
            ex.printStackTrace();
        }
    }

    static StreamSource getSource(String x) throws Exception {
        Reader rdr = null;
        if ("-".equals(x)) rdr = new InputStreamReader(System.in); else if (x.indexOf("<?xml") >= 0) rdr = new StringReader(x); else if (x.indexOf("://") > 0) rdr = new InputStreamReader(new URL(x).openStream()); else {
            File f = null;
            if (x.length() < 255 && (f = new File(x)).exists()) {
                rdr = new FileReader(f);
                xslname = f.getName();
            } else if (x.indexOf('<') >= 0) rdr = new StringReader(x);
        }
        StreamSource ssrc = new StreamSource(rdr);
        return ssrc;
    }
}
