import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ConvertTab2TopMatches {

    public static void main(String[] args) {
        String listfile = args[0];
        Hashtable tabmapping = new Hashtable();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(listfile));
            while (reader.ready()) {
                String[] line = reader.readLine().split("\t");
                if (!tabmapping.containsKey(line[0])) tabmapping.put(line[0], new Vector());
                ((Vector) tabmapping.get(line[0])).add(line[1]);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        int found = 0;
        int allfound = 0;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element root = (Element) document.createElement("results");
            document.appendChild(root);
            Object[] keys = tabmapping.keySet().toArray();
            Arrays.sort(keys);
            for (int l = 0; l < keys.length; l++) {
                String query = (String) keys[l];
                Element qElem = document.createElement("query");
                qElem.setAttribute("name", query);
                root.appendChild(qElem);
                Vector top = (Vector) tabmapping.get(query);
                if (top.size() > 0) {
                    qElem.setAttribute("length", "0");
                    found++;
                    for (int i = 0; i < top.size(); i++) {
                        allfound++;
                        Element tElem = document.createElement("target");
                        tElem.setAttribute("ondexid", (String) top.get(i));
                        tElem.setAttribute("taxid", "0");
                        tElem.setAttribute("cv", "imported");
                        tElem.setAttribute("length", "0");
                        qElem.appendChild(tElem);
                        Element mElem = document.createElement("match");
                        mElem.setAttribute("score", "0");
                        mElem.setAttribute("evalue", "0");
                        mElem.setAttribute("length", "0");
                        tElem.appendChild(mElem);
                    }
                } else {
                    qElem.appendChild(document.createTextNode("nothing found"));
                }
            }
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            FileOutputStream stream = new FileOutputStream(listfile + ".top.xml");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(stream);
            transformer.transform(source, result);
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerConfigurationException tce) {
            System.out.println("\n** Transformer Factory error");
            System.out.println("   " + tce.getMessage());
            Throwable x = tce;
            if (tce.getException() != null) x = tce.getException();
            x.printStackTrace();
        } catch (TransformerException te) {
            System.out.println("\n** Transformation error");
            System.out.println("   " + te.getMessage());
            Throwable x = te;
            if (te.getException() != null) x = te.getException();
            x.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        System.out.println("Queries found: " + found + " total of matches: " + allfound);
    }
}
