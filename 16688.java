import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import modules.base.res.Category;
import modules.base.res.Partner;
import modules.base.res.User;
import org.mga.common.ColumFactory;
import org.mga.common.LoggerManager;
import org.mga.common.SQLObject;
import org.mga.common.SearchKey;
import org.mga.common.fields.DateTime;
import org.mga.core.ConnectionManager;
import org.mga.core.ModuleLoader;
import org.mga.core.ObjectPool;
import org.mga.core.ServiceManager;
import org.mga.core.parsor.TypeConverter;
import org.mga.tools.TypeConvertor;

public class Server {

    private ResourceBundle config = null;

    private ServiceManager manager = null;

    private final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(this.getClass().toString());

    private ConnectionManager cnManager = null;

    private ColumFactory colFactory = null;

    /**
	 *
	 */
    public Server() {
        config = ResourceBundle.getBundle("properties/server", new Locale("en", "US"));
        String log = config.getString("logger");
        new LoggerManager(log);
        logger.addHandler(org.mga.common.LoggerManager.getHandler());
        logger.log(Level.INFO, "log file " + log);
        colFactory = new ColumFactory(config.getString("columfactory"));
        int port = Integer.parseInt(config.getString("port"));
        logger.log(Level.INFO, "server port " + port);
        String service = config.getString("service");
        logger.log(Level.INFO, "service configuration file " + service);
        cnManager = new ConnectionManager(config);
        manager = new ServiceManager(service, port);
        manager.startServer();
        logger.log(Level.INFO, "starting to load all services ");
        new ModuleLoader(config);
    }

    /**
	 * @param args
	 * @throws IOException
	 */
    public static void main(String[] args) {
        new Server();
    }
}
