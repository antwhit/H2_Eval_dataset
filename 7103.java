import java.awt.Image;
import java.io.StringReader;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;

public class Submission {

    public String hostIP;

    public String groupID;

    public String xml = "<root>" + "<weight>" + "<light>" + "</light>" + "<heavy>" + "</heavy>" + "</weight>" + "<contour>" + "</contour>" + "</root>";

    public Image[] images;

    TreeNode getClassificationTree() throws Exception {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Classification");
        DOMParser p = new DOMParser();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(xml));
        p.parse(is);
        Document doc = p.getDocument();
        XPath xpath = XPathFactory.newInstance().newXPath();
        String expression = "/root/model";
        Node model = (Node) xpath.evaluate(expression, doc, XPathConstants.NODE);
        Element xmlFeature = (Element) model.getFirstChild();
        while (xmlFeature != null) {
            DefaultMutableTreeNode feature = new DefaultMutableTreeNode(xmlFeature.getAttribute("name"));
            root.add(feature);
            Element xmlValue = (Element) xmlFeature.getFirstChild();
            while (xmlValue != null) {
                FeatureValue fv = new FeatureValue();
                fv.name = xmlValue.getAttribute("name");
                String imageID = (String) xpath.evaluate("image", xmlValue, XPathConstants.STRING);
                Image img = null;
                try {
                    int imageIndex = Integer.parseInt(imageID);
                    img = this.images[imageIndex];
                } catch (Exception e) {
                }
                fv.image = img;
                fv.description = (String) xpath.evaluate("description", xmlValue, XPathConstants.STRING);
                DefaultMutableTreeNode value = new DefaultMutableTreeNode(fv);
                feature.add(value);
                xmlValue = (Element) xmlValue.getNextSibling();
            }
            xmlFeature = (Element) xmlFeature.getNextSibling();
        }
        return root;
    }
}
