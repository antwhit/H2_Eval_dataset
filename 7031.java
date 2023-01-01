import junit.framework.*;
import pub.utils.Log;
import pub.db.command.AddUser;
import pub.db.*;
import pub.test.DatabaseTestCase;
import pub.beans.*;
import pub.beans.factories.UserBeanFactory;

public class AddUserTest extends DatabaseTestCase {

    AddUser cmd;

    UserTable userTable;

    public void setUp() throws Exception {
        super.setUp();
        cmd = new AddUser(conn);
        userTable = new UserTable(conn);
    }

    public void testAddSingleUser() {
        cmd.setName("somenewuser");
        cmd.setPassword("abracadabra");
        cmd.setCanLogin(true);
        cmd.setIsEditor(true);
        cmd.setCanAccessPdfs(true);
        cmd.execute();
        userTable.lookup("somenewuser");
    }

    public void testAttributesAfterInsert() {
        cmd.setName("somenewuser");
        cmd.setPassword("abracadabra");
        cmd.setCanLogin(true);
        cmd.setIsEditor(true);
        cmd.setCanAccessPdfs(true);
        cmd.execute();
        UserBean userBean = (new UserBeanFactory()).getLoginBean(conn, "somenewuser", "abracadabra");
        assertFalse(userBean.isNull());
        assertTrue(userBean.canLogin());
        assertTrue(userBean.isEditor());
        assertTrue(userBean.canAccessPdfs());
        cmd.setName("useless");
        cmd.setPassword("foo");
        cmd.setCanLogin(false);
        cmd.setIsEditor(false);
        cmd.setCanAccessPdfs(false);
        cmd.execute();
        assertFalse(userBean.isNull());
        assertEquals(true, userBean.canLogin());
        UserBean uselessBean = (new UserBeanFactory()).getLoginBean(conn, "useless", "foo");
        assertFalse(uselessBean.isNull());
        assertFalse(uselessBean.canLogin());
        assertFalse(uselessBean.isEditor());
        assertFalse(uselessBean.canAccessPdfs());
    }

    public void testAddingDuplicateUser() {
        cmd.setName("somenewuser");
        cmd.setPassword("abracadabra");
        cmd.execute();
        try {
            cmd.setName("somenewuser");
            cmd.setPassword("abracadabra");
            cmd.execute();
            fail("duplicate user");
        } catch (RollbackException e) {
            Log.getLogger(this.getClass()).debug("Expected");
        }
    }
}
