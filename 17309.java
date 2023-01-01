import com.bs.xdbms.datamodel.*;
import com.bs.xdbms.persistence.*;
import java.sql.*;
import java.io.PrintWriter;
import java.io.IOException;
import org.w3c.dom.*;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.Serializer;
import org.apache.xml.serialize.XMLSerializer;
import org.apache.xml.serialize.SerializerFactory;

public class DBToXMLTest extends DataModelLoadTest {

    public static void main(String[] args) throws XModelException {
        System.out.println("\n---DBToXMLTest begins---");
        DBToXMLTest d2x = new DBToXMLTest();
        try {
            d2x.run();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("DBToXMLTest fails.");
            return;
        }
        System.out.println("---DBToXMLTest finished successfully---");
    }

    public void run() throws XModelException {
        println("Building a model structure from the sample database...");
        XDocument xdoc = null;
        try {
            xdoc = load("part0001.xml");
        } catch (SQLException e) {
            throw new XModelException(e.toString());
        } catch (XModelException me) {
            throw me;
        }
        println("Converting XDocument to DOM Document...");
        Document doc = null;
        try {
            doc = convert(xdoc);
        } catch (DOMException e) {
            e.printStackTrace();
            throw new XModelException(e.toString());
        }
        PrintWriter writer = new PrintWriter(System.out);
        OutputFormat format = new OutputFormat(doc, "UTF-8", true);
        XMLSerializer ser = new XMLSerializer(writer, format);
        try {
            ser.asDOMSerializer();
            ser.serialize(doc);
        } catch (IOException e) {
            throw new XModelException(e.toString());
        }
    }

    private Document convert(XDocument xdoc) throws DOMException {
        ModelToDOM m2d = new ModelToDOM();
        Document doc = m2d.convert(xdoc);
        return doc;
    }
}
