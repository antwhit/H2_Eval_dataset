import com.bs.xdbms.datamodel.*;
import com.bs.xdbms.persistence.*;
import java.sql.*;
import org.xml.sax.SAXException;

public class XMLToDBTest extends DataModelStoreTest {

    private String XML = "part0001.xml";

    public static void main(String[] args) throws XModelException {
        System.out.println("\n---XMLToDBTest begins---");
        XMLToDBTest xml2db = new XMLToDBTest();
        try {
            xml2db.run();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("XMLToDBTest fails.");
            return;
        }
        System.out.println("---XMLToDBTest finished successfully---");
    }

    public void run() throws XModelException {
        println("Building a model from " + XML + "...");
        XDocument xdoc = populate();
        try {
            store(xdoc);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new XModelException(e.toString());
        }
    }

    protected XDocument populate() throws XModelException {
        SAXToModel s2m = new SAXToModel();
        XDocument xdoc = null;
        try {
            xdoc = s2m.parse(XML);
        } catch (Exception e) {
            throw new XModelException(e.toString());
        }
        return xdoc;
    }

    private void store(XDocument xdoc) throws SQLException {
        Connection conn = null;
        try {
            println("Trying to get a JDBC connection...");
            conn = getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.close();
            } catch (SQLException se) {
            }
            return;
        }
        DBUpdater updater = null;
        try {
            updater = new DBUpdater(conn);
            println("Trying to insert the model in the database tables...");
            updater.insert(xdoc);
        } catch (SQLException e) {
            e.printStackTrace();
            updater.rollback();
            try {
                conn.close();
            } catch (SQLException se) {
            }
            return;
        }
        updater.commit();
        try {
            conn.close();
        } catch (SQLException e) {
        }
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class driver = Class.forName(JDBC_DRIVER);
        Connection conn = DriverManager.getConnection(DB_URL, USER_ID, PASSWORD);
        return conn;
    }
}
