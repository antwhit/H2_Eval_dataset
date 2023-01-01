import java.io.IOException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class XmlJDom {

    public static void main(String[] args) {
        try {
            Document doc = new SAXBuilder().build("config.xml");
            Element config = doc.getRootElement();
            for (Object o : config.getChildren("section")) {
                Element section = (Element) o;
                String name = section.getAttributeValue("name");
                System.out.println("========== " + name + " ===========");
                for (Object o1 : section.getChildren("parameter")) {
                    Element parameter = (Element) o1;
                    String name1 = parameter.getAttributeValue("name");
                    String value = parameter.getAttributeValue("value");
                    System.out.println(name + "=" + value);
                    parameter.setAttribute("value", "test");
                }
                Element neu = new Element("parameter");
                neu.setAttribute("name", "neu");
                neu.setAttribute("value", "test-neu");
                section.addContent(neu);
            }
            XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
            out.output(doc, System.out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
