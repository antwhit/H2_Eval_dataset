import java.sql.*;

/**
 * 
 * @author Master
 */
public class Select {

    private static void out(String s) {
        System.out.println(s);
    }

    private static String outSelect(ResultSet rs, String[] columns) throws SQLException {
        String out = "";
        for (int i = 0; i < columns.length; i++) {
            out += columns[i] + " : " + rs.getString(columns[i]) + "; ";
        }
        return out;
    }

    public static void main(String[] args) {
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String server = "jdbc:sqlserver://localhost:1433";
        String props = ";integratedSecurity=false;database=javaidiot;username=user;password=user123;";
        String query = "SELECT * FROM testTable;";
        String[] columns = { "id", "sometext", "someints" };
        Query qr = new Query(driver, server, props, query);
        try {
            ResultSet rs = qr.toResultSet();
            try {
                while (rs.next()) {
                    out(outSelect(rs, columns));
                }
            } catch (NullPointerException e) {
                out(e.getMessage());
            }
        } catch (SQLException e) {
            out(e.getMessage());
        } catch (NullPointerException e) {
            out(e.getMessage());
        }
        qr.close();
        out(qr.debug);
    }
}
