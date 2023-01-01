import java.util.Vector;
import java.io.*;
import org.xml.sax.*;
import org.xml.sax.XMLReader;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by IntelliJ IDEA.
 * User: nathan
 * Date: Jun 18, 2008
 * Time: 12:15:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProteinLoader extends DefaultHandler {

    private Vector<Protein> proteinList;

    private Vector<String> fNames;

    private double peptideCutoff = 0;

    private double pendingCount = 0;

    private Vector<String> proteinInfo = null;

    private int nRuns;

    private int curRun;

    private Protein prot;

    public ProteinLoader(Vector<String> fnames) {
        super();
        fNames = fnames;
        nRuns = fNames.size();
        proteinList = new Vector<Protein>();
        try {
            for (int i = nRuns; i-- > 0; ) this.parseFile(fNames.get(i), i);
        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    public Vector<Protein> getProteinList() {
        return this.proteinList;
    }

    public int getnRuns() {
        return this.nRuns;
    }

    void parseFile(String fname, int runNum) throws Exception {
        XMLReader xr = XMLReaderFactory.createXMLReader();
        xr.setContentHandler(this);
        curRun = runNum;
        FileReader fr = new FileReader(fname);
        xr.parse(new InputSource(fr));
        fr.close();
    }

    public void startDocument() {
    }

    public void endDocument() {
    }

    public void startElement(String uri, String name, String qName, Attributes atts) {
        if (qName.equalsIgnoreCase("protein")) {
            proteinInfo = new Vector<String>();
            proteinInfo.add(atts.getValue("protein_name"));
            pendingCount = 0;
            prot = null;
            for (int i = proteinList.size(); i-- > 0; ) {
                if (proteinInfo.equals(proteinList.get(i).getProteinInfo())) {
                    prot = proteinList.get(i);
                }
            }
        } else if (qName.equalsIgnoreCase("peptide")) {
            if (Double.parseDouble(atts.getValue("nsp_adjusted_probability")) >= peptideCutoff) {
                pendingCount += Double.parseDouble(atts.getValue("n_instances"));
            }
        }
    }

    public void endElement(String uri, String name, String qName) {
        if (qName.equalsIgnoreCase("protein")) {
            if (prot != null) {
                prot.insertCount(pendingCount, curRun);
            } else {
                prot = new Protein(proteinInfo, nRuns);
                prot.insertCount(pendingCount, curRun);
                proteinList.add(prot);
            }
        }
    }

    public class helper implements EntityResolver {

        public InputSource resolveEntity(String a, String b) throws SAXException {
            return new InputSource(new StringReader(""));
        }
    }
}
