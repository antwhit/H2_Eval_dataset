import java.io.*;
import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;

public class CreateXMLDocuments {

    public static void main(String[] args) {
        try {
            FilenameFilter filter = new FilenameFilter() {

                public boolean accept(File dir, String name) {
                    if (name.endsWith(".txt")) return true;
                    return false;
                }
            };
            String[] txtDocuments = new File("texts").list(filter);
            for (int i = 0; i < txtDocuments.length; i++) {
                String[] arguments = new String[1];
                arguments[0] = "texts/" + txtDocuments[i];
                Txt2WikiXML_fileOutput.main(arguments);
                setWikiName(txtDocuments[i].substring(0, txtDocuments[i].length() - 4));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private static void setWikiName(String name) {
        try {
            SAXBuilder b = new SAXBuilder();
            Document d = b.build(new File("documents/" + name + ".xml"));
            Element rootElement = d.getRootElement();
            rootElement.setAttribute("name", name);
            XMLOutputter outputter = new XMLOutputter();
            outputter.output(d, new FileWriter("documents/" + name + ".xml"));
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
