import java.sql.*;
import java.io.*;
import java.util.*;

public class connect {

    Statement stmt;

    Connection con;

    public connect() {
        connect();
    }

    public void connect() {
        try {
            String url;
            BufferedReader x = new BufferedReader(new FileReader("database.txt"));
            String file = x.readLine();
            x.close();
            url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=";
            url += file.trim() + ";DriverID=22}";
            try {
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                con = DriverManager.getConnection(url, "", "");
                stmt = con.createStatement();
            } catch (ClassNotFoundException cnfe) {
                System.err.println(cnfe);
            }
        } catch (SQLException sqle) {
            System.err.println(sqle);
        } catch (FileNotFoundException cnfe) {
            System.err.println(cnfe);
        } catch (IOException cnfe) {
            System.err.println(cnfe);
        }
    }

    public ResultSet select(String x) {
        try {
            ResultSet r = stmt.executeQuery(x);
            return r;
        } catch (SQLException sqle) {
            System.err.println(sqle);
            return null;
        }
    }

    public void del(String x) {
        try {
            stmt.executeUpdate(x);
        } catch (SQLException sqle) {
            System.err.println(sqle);
        }
    }

    public void update(String x) {
        try {
            stmt.executeUpdate(x);
            System.out.println(" Success  update");
        } catch (SQLException sqle) {
            System.err.println(sqle);
        }
    }

    public void close2() {
        try {
            con.close();
        } catch (SQLException sqle) {
            System.err.println(sqle);
        }
    }

    public void add(String x) {
        try {
            stmt.executeUpdate(x);
            con.commit();
            con.close();
        } catch (SQLException sqle) {
            System.err.println(sqle);
        }
    }
}
