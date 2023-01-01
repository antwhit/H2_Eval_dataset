import java.sql.Connection;

/**
 * @author Markus Plessing
 */
public class ConnectDB {

    private static UserProperties userprops;

    private static DesEncrypter encrypter = new DesEncrypter("gulli15x30pulli");

    /**
		 * the Connection to the Database
		 */
    public static Connection con = null;

    /**
		 * should the Errordialog be shown
		 */
    public static boolean showInfo = true;

    private static final String driver = "org.postgresql.Driver";

    private static String url;

    private static String user;

    private static String password;

    /**
	 * Constructor for ConnectDB
	 * not used : all methods are static
	 */
    public ConnectDB() {
    }

    /**
	  * connect to Database
	  * @return the Connection to the DB
	  */
    public static Connection connect() {
        try {
            userprops = new UserProperties("user.properties");
            user = userprops.get("DBUser");
            password = encrypter.decrypt(userprops.get("DBPass").toString());
            StringBuffer buff = new StringBuffer();
            buff.append("jdbc:postgresql://[");
            buff.append(userprops.get("DBHost"));
            buff.append("]:5432/");
            buff.append(userprops.get("DBName"));
            url = buff.toString();
            Class.forName(driver).newInstance();
            con = java.sql.DriverManager.getConnection(url, user, password);
        } catch (Exception ex) {
            ex.printStackTrace();
            if (showInfo) new ErrorHandler(ex);
        }
        return con;
    }
}
