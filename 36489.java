import javax.swing.table.AbstractTableModel;
import java.sql.*;

/**
 *ResultSet rows and columns are counted from 1 and JTable
 *rows and columns are counted from 0. When processing
 *ResultSet rows or columns for use in a JTable, it is
 *necessary to add 1 to the row or column number to manipulate
 *the appropriate ResultSet column (i.e., JTable column 0 is
 *ResultSet column 1 and JTable row 0 is ResultSet row 1)
 */
public class ResultSetTableModel extends AbstractTableModel {

    private Connection connection;

    private Statement statement;

    private ResultSet resultSet;

    private ResultSetMetaData metaData;

    private int numberOfRows;

    private boolean connectedToDatabase = false;

    public ResultSetTableModel(String driver, String url, String query) throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        connection = DriverManager.getConnection(url);
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        connectedToDatabase = true;
        setQuery(query);
    }

    public Class getColumnClass(int column) throws IllegalStateException {
        if (!connectedToDatabase) throw new IllegalStateException("Not Connected to Database");
        try {
            String className = metaData.getColumnClassName(column + 1);
            return Class.forName(className);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return Object.class;
    }

    public int getColumnCount() throws IllegalStateException {
        if (!connectedToDatabase) throw new IllegalStateException("Not Connected to Database");
        try {
            return metaData.getColumnCount();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return 0;
    }

    public String getColumnName(int column) throws IllegalStateException {
        if (!connectedToDatabase) throw new IllegalStateException("Not Connected to Database");
        try {
            return metaData.getColumnName(column + 1);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return "";
    }

    public int getRowCount() throws IllegalStateException {
        if (!connectedToDatabase) throw new IllegalStateException("Not Connected to Database");
        return numberOfRows;
    }

    public Object getValueAt(int row, int column) throws IllegalStateException {
        if (!connectedToDatabase) throw new IllegalStateException("Not Connected to Database");
        try {
            resultSet.absolute(row + 1);
            return resultSet.getObject(column + 1);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return "";
    }

    public void setQuery(String query) throws SQLException, IllegalStateException {
        if (!connectedToDatabase) throw new IllegalStateException("Not Connected to Database");
        resultSet = statement.executeQuery(query);
        metaData = resultSet.getMetaData();
        resultSet.last();
        numberOfRows = resultSet.getRow();
        fireTableStructureChanged();
    }

    public void disconnectFromDatabase() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            connectedToDatabase = false;
        }
    }
}
