import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    private static DataBaseConnection instance = null;

    private Connection conn = null;

    private DataBaseConnection() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
    }

    public static DataBaseConnection getInstance() throws ClassNotFoundException {
        if (instance == null) {
            instance = new DataBaseConnection();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/pb", "hugowschneider", "metallica");
        }
        return conn;
    }

    public void closeConnection() throws SQLException {
        conn.close();
        conn = null;
    }
}
