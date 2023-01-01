import java.sql.*;
import javax.naming.*;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.DataSources;

/**
 *  This example shows how to acquire a c3p0 DataSource and
 *  bind it to a JNDI name service.
 */
public final class JndiBindDataSource {

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] argv) {
        try {
            String jndiName = argv[0];
            DataSource unpooled = DataSources.unpooledDataSource("jdbc:postgresql://localhost/test", "swaldman", "test");
            DataSource pooled = DataSources.pooledDataSource(unpooled);
            InitialContext ctx = new InitialContext();
            ctx.rebind(jndiName, pooled);
            System.out.println("DataSource bound to nameservice under the name \"" + jndiName + '\"');
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void attemptClose(ResultSet o) {
        try {
            if (o != null) o.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void attemptClose(Statement o) {
        try {
            if (o != null) o.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void attemptClose(Connection o) {
        try {
            if (o != null) o.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JndiBindDataSource() {
    }
}
