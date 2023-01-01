import java.util.ArrayList;
import java.util.Hashtable;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.XmlException;
import xregistry.XregistryException;
import edu.indiana.extreme.gfac.schemas.SchemaValidator;
import edu.indiana.extreme.gfac.wsdl.WSDLConstants;
import edu.indiana.extreme.gfac.wsdl.WSDLGenerator;
import edu.indiana.extreme.namespaces.x2004.x01.gFac.ServiceMapDocument;
import edu.indiana.extreme.namespaces.x2004.x01.gFac.ServiceMapType;

public class TestUtils {

    public static void testCantainmentArray(String[][] names, String value) throws Exception {
        for (String[] name : names) {
            if (name[0].equals(value)) {
                return;
            }
        }
        throw new Exception(value + " Value is Not found ");
    }

    public static void testCantainment(String[] names, String value) throws Exception {
        for (String name : names) {
            if (name.equals(value)) {
                return;
            }
        }
        throw new Exception(value + " Value is Not found ");
    }

    public static void printList(String[] names) {
        System.out.println();
        for (String name : names) {
            System.out.print(name + " ");
        }
    }

    public static void printListArray(String[][] names) {
        System.out.println();
        for (String[] name : names) {
            System.out.print(name[0] + " " + name[1]);
        }
    }
}
