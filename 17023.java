import java.sql.*;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.DataSources;

/**
 *  This example shows how to programmatically get and directly use
 *  an unpooled DataSource
 */
public final class UseUnpooledDataSource {

    public static void main(String[] argv) {
        try {
            DataSource ds = DataSources.unpooledDataSource("jdbc:postgresql://localhost/test", "swaldman", "test");
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            try {
                con = ds.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT * FROM foo");
                while (rs.next()) System.out.println(rs.getString(1));
            } finally {
                attemptClose(rs);
                attemptClose(stmt);
                attemptClose(con);
            }
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

    private UseUnpooledDataSource() {
    }
}
