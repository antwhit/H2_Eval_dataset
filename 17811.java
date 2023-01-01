import java.io.IOException;
import java.sql.*;
import java.util.Hashtable;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * With this class you can easily store and fetch configuration data.
 * @version $Id: ConfigImpl.java,v 1.1 2001/07/22 20:18:51 brightice Exp $
 * @author Matthias Juchem <matthias at konfido.de>
 */
public final class ConfigImpl implements Configuration {

    private int buffReq;

    private int unbuffReq;

    private Hashtable data = null;

    private ServletConfig servletConfig = null;

    private DBConnection dbConnection = null;

    public ConfigImpl(ServletConfig servletConfig) {
        if (servletConfig == null) throw new NullPointerException("ConfigImpl.servletConfig must not be null.");
        this.servletConfig = servletConfig;
        data = new Hashtable();
        if (data == null) throw new NullPointerException("ConfigImpl.data could not be initialized.");
    }

    public void setDBConnection(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public String getValue(String moduleName, String key1, String key2) {
        synchronized (data) {
            if (!data.containsKey(getIKey(moduleName, key1, key2))) {
                unbuffReq++;
                String arguments[] = { moduleName, key1, key2 };
                ResultSet rs = dbConnection.preparedQuery("SELECT value FROM config WHERE module=? " + " AND key1=? AND key2=?", arguments);
                try {
                    if (rs.first()) {
                        data.put(getIKey(moduleName, key1, key2), rs.getString(1));
                    } else {
                        return (null);
                    }
                } catch (SQLException e) {
                }
            } else {
                buffReq++;
            }
            return ((String) data.get(getIKey(moduleName, key1, key2)));
        }
    }

    public void putValue(String moduleName, String key1, String key2, String value, boolean overwrite) {
        String insertArguments[] = { moduleName, key1, key2, value };
        String updateArguments[] = { value, moduleName, key1, key2 };
        String myIKey = getIKey(moduleName, key1, key2);
        int result = 0;
        synchronized (data) {
            if (!data.containsKey(myIKey)) {
                result = dbConnection.preparedUpdateByKey("config", "addEntry", insertArguments);
            }
            if (overwrite && (result != 1 || data.containsKey(myIKey))) {
                result = dbConnection.preparedUpdateByKey("config", "changeEntry", updateArguments);
            }
            if (result == 1) {
                data.put(myIKey, value);
            }
        }
    }

    public void putValue(String moduleName, String key1, String key2, String value) {
        putValue(moduleName, key1, key2, value, false);
    }

    public void removeValue(String moduleName, String key1, String key2) {
        String arguments[] = { moduleName, key1, key2 };
        int result = 0;
        synchronized (data) {
            result = dbConnection.preparedUpdateByKey("config", "deleteEntry", arguments);
            if (result == 1 && data.containsKey(getIKey(moduleName, key1, key2))) {
                data.remove(getIKey(moduleName, key1, key2));
            }
        }
    }

    private String getIKey(String moduleName, String key1, String key2) {
        return (moduleName + "\n\n" + key1 + "\n" + key2);
    }

    public String getParameterName(String moduleName, String parameter) {
        return (this.getValue(moduleName, "_paramname", parameter));
    }

    public String getSQLQueryString(String moduleName, String stringName) {
        return (this.getValue(moduleName, "_SQL_QUERY", stringName));
    }

    public String getSQLUpdateString(String moduleName, String stringName) {
        return (this.getValue(moduleName, "_SQL_UPDATE", stringName));
    }

    public int getNumRequests() {
        return (buffReq + unbuffReq);
    }

    public int getNumBufferedRequests() {
        return (buffReq);
    }

    public int getNumUnbufferedRequests() {
        return (unbuffReq);
    }

    public void clearBuffer() {
        data.clear();
    }
}
