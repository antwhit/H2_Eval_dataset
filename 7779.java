import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import junit.framework.TestCase;

public class InsertXMLToDBTest extends TestCase {

    private InsertXMLToDB insert;

    public void setUp() {
        insert = new InsertXMLToDB();
    }

    public void tearDown() {
        insert = null;
    }

    public void testCreateTable() {
        try {
            insert.createTable();
        } catch (Exception e) {
            assertEquals("ClassNotFoundException ", e.getClass(), ClassNotFoundException.class);
        }
    }

    public void testInsertXML() {
        try {
            insert.insertXML();
        } catch (Exception e) {
            assertEquals("NullPointerException ", e.getClass(), NullPointerException.class);
        }
    }

    public void testSelectWriteXML() {
        try {
            insert.selectWriteXML();
        } catch (Exception e) {
            assertEquals("NullPointerException ", e.getClass(), NullPointerException.class);
        }
    }
}
