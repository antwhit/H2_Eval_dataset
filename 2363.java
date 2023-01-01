import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseStart {

    public static void main(String[] args) {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        Connection connection = null;
        try {
            DriverManager.getConnection("jdbc:hsqldb:file:/home/shinchan/Desktop/db", "sa", "");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
