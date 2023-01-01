import java.sql.*;

public class DB {

    public static Connection conn = null;

    private static final DB _theInstance = new DB();

    private DB() {
        try {
            connect();
        } catch (Exception e) {
            System.out.println("Can't connect to DB " + e);
        }
    }

    public static DB getInstance() {
        if (_theInstance == null) {
            throw new RuntimeException("No singleton instance available");
        } else {
            return _theInstance;
        }
    }

    private boolean checkConnection() {
        try {
            conn.getMetaData();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            System.out.println("JDBC Not loaded! " + ex);
            System.exit(-1);
        }
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/proxy?" + "user=work&password=w0rk1nG");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            System.exit(-1);
        }
    }

    public void execute(String sql) throws SQLException {
        if (!checkConnection()) {
            connect();
        }
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            if (stmt.execute(sql)) {
                stmt = null;
            }
        } catch (SQLException e) {
            stmt = null;
            System.out.println(e);
        }
    }

    public ResultSet getResult(String sql) throws SQLException {
        if (!checkConnection()) {
            connect();
        }
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            if (stmt.execute(sql)) {
                ResultSet rs = stmt.getResultSet();
                stmt = null;
                return rs;
            }
        } catch (SQLException e) {
            stmt = null;
            System.out.println("Can't get resultSet " + e);
        }
        return null;
    }

    public void execute(String sql, String[] params) throws SQLException {
        if (!checkConnection()) {
            connect();
        }
        try {
            PreparedStatement prepStmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                prepStmt.setString(i + 1, params[i]);
            }
            prepStmt.executeUpdate();
            prepStmt.close();
        } catch (SQLException e) {
            System.out.println("Execute failed with " + e);
        }
    }
}
