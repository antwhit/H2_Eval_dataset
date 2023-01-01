import org.dcm4che.data.DcmObjectFactory;
import org.dcm4che.data.DcmValueException;
import org.dcm4che.data.Dataset;
import org.xml.sax.SAXException;
import java.io.*;
import javax.xml.parsers.*;
import gnu.getopt.*;

/**
 *
 * @author  gunter.zeilinger@tiani.com
 */
public class Xml2Dcm {

    private Dataset ds = DcmObjectFactory.getInstance().newDataset();

    /** Creates a new instance of Xml2Dcm */
    public Xml2Dcm() {
    }

    public void read(DataInputStream in) throws IOException, DcmValueException {
        ds.clear();
        try {
            ds.readFile(in, null, -1);
        } finally {
            try {
                in.close();
            } catch (IOException ignore) {
            }
        }
    }

    public void process(String xml_file, DataOutputStream out) throws IOException, DcmValueException, ParserConfigurationException, SAXException {
        try {
            SAXParserFactory f = SAXParserFactory.newInstance();
            SAXParser p = f.newSAXParser();
            p.parse(new File(xml_file), ds.getSAXHandler());
            ds.writeFile(out, null);
        } finally {
            try {
                out.close();
            } catch (IOException ignore) {
            }
        }
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) throws Exception {
        Getopt g = new Getopt("xml2dcm.jar", args, "i:");
        Xml2Dcm xml2dcm = new Xml2Dcm();
        int c;
        while ((c = g.getopt()) != -1) {
            switch(c) {
                case 'i':
                    xml2dcm.read(new DataInputStream(new BufferedInputStream(new FileInputStream(g.getOptarg()))));
                    break;
                case '?':
                    exit("");
                    break;
            }
        }
        int optind = g.getOptind();
        int argc = args.length - optind;
        if (argc < 2) {
            exit("xml2dcm.jar: Missing argument\n");
        }
        if (argc > 2) {
            exit("xml2dcm.jar: To many arguments\n");
        }
        xml2dcm.process(args[optind], new DataOutputStream(new BufferedOutputStream(new FileOutputStream(args[optind + 1]))));
    }

    private static void exit(String prompt) {
        System.err.println(prompt);
        System.err.println(USAGE);
        System.exit(1);
    }

    private static final String USAGE = "Usage: java -jar xml2dcm.jar [-i <dcm_file>] <xml_file> <dcm_file>\n\n" + "Create or update DICOM file <dcm_file> according XML specification <xml_file>.\n\n" + "Options:\n" + " -i <dcm_file>  Update specified DICOM file but store it as new one.\n";
}
