import java.io.*;
import java.sql.*;
import javax.sql.*;
import javax.sql.rowset.*;
import com.sun.rowset.*;

public class JdbcRowSetSample {

    public static void main(String[] args) {
        String dbUrl;
        String dbUserId;
        String dbPasswd;
        String dbDriver;
        String dbCommand = "select * from employees";
        ResultSet res = null;
        Connection con = null;
        Statement stmt = null;
        JdbcRowSet jdbcRs;
        dbUrl = args[0];
        System.out.println("Url: " + dbUrl);
        dbUserId = args[1];
        System.out.println("UserId: " + dbUserId);
        dbPasswd = args[2];
        System.out.println("Password: " + dbPasswd);
        dbDriver = args[3];
        System.out.println("Driver: " + dbDriver);
        try {
            Class.forName(dbDriver);
        } catch (Exception e) {
            System.out.println("Unable to load driver: " + e.getMessage());
            System.exit(1);
        }
        try {
            con = DriverManager.getConnection(dbUrl, dbUserId, dbPasswd);
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            res = stmt.executeQuery("select * from COFFES");
            jdbcRs = new JdbcRowSetImpl(res);
            jdbcRs.absolute(3);
            jdbcRs.updateFloat("PRICE", 10.99f);
            jdbcRs.updateRow();
            jdbcRs.first();
            jdbcRs.moveToInsertRow();
            jdbcRs.updateString("COF_NAME", "HouseBlend");
            jdbcRs.updateInt("SUP_ID", 49);
            jdbcRs.updateFloat("PRICE", 7.99f);
            jdbcRs.updateInt("SALES", 0);
            jdbcRs.updateInt("TOTAL", 0);
            jdbcRs.insertRow();
            jdbcRs.moveToCurrentRow();
            jdbcRs.moveToInsertRow();
            jdbcRs.updateString("COF_NAME", "HouseDecaf");
            jdbcRs.updateInt("SUP_ID", 49);
            jdbcRs.updateFloat("PRICE", 8.99f);
            jdbcRs.updateInt("SALES", 0);
            jdbcRs.updateInt("TOTAL", 0);
            jdbcRs.insertRow();
            jdbcRs.moveToCurrentRow();
            jdbcRs.last();
            jdbcRs.deleteRow();
            jdbcRs.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("Caught SQLException: " + sqle.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unexpected Exception caught: " + e.getMessage());
        }
    }
}
