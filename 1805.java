import junit.framework.TestCase;

public class SelectFromDBTest5 extends TestCase {

    SelectFromDB select;

    public void setUp() {
        select = new SelectFromDB();
    }

    public void tearDown() {
        select = null;
    }

    public void testSelectFromDB() {
        try {
            select.selectFromDB();
        } catch (Exception e) {
            assertEquals("ClassNotFoundException", e.getClass(), ClassNotFoundException.class);
        }
    }
}
