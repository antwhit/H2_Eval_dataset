import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;
import java.util.Enumeration;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.InetAddress;

/**
 * Class TBBS
 *
 * The TBBS class is the main class of the TBBS server. This class is
 * instantiated by main, and starts the server. This class handles
 * configuration of the server, listening on the sockets, and handling
 * the client threads. Configuration is handled in the %TBBS_HOME%\conf\tbbs.xml
 * file. See the startup scripts in the bin for more information. However, the
 * the config file isn't really being used right now. It is, however, being parsed
 * so it must be valid. It is an xml doc, so it's validity should probably be checked
 * early in the program, like in the main for example. The adding of services is
 * handled in the config file. See if for more info.
 *
 * @author	<a href="mailto:mesullvn@uiuc.edu">Mark Sullivan</a>
 * @version 1.0
 * @requires jdk1.3+
 *
 */
public class TBBS {

    private ServerConfig serverConfig;

    private Server server = null;

    /**
	 * Constructor
	 *
	 * @param	ServerConfig object to configure the server with.
	 */
    public TBBS(ServerConfig sc) {
        try {
            this.serverConfig = sc;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
	 * Starts the server thread listening on an endless loop on a socket. In order
	 * to make sure there is only one server running at a time, and also so that
	 * the server can be gracefully stopped, another maintence port should be started
	 * to listen for admin commands like 'stop'
	 */
    public void start() {
        server = new Server(serverConfig);
        server.start();
    }

    /**
	 * main - entry point into program. This starts an
	 * instance of TBBS. Also checks for a valid config file before
	 * proceeding. If no valid config file is found, then the system
	 * exits with error code 1. This should be expanded in the future
	 * to check all the values in the config file to make sure they
	 * are valid. However, the config file isn't really being used
	 * at the moment.
	 *
	 * @param	args	command line arguments passed from system
	 *
	 */
    public static void main(String[] args) throws Exception {
        try {
            if (args.length < 2) {
                System.out.println("Usage: java <classpath> TBBS start config_file");
                System.exit(1);
            } else {
                try {
                    new FileInputStream(args[1]);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    System.out.println("Config file not found. Check startup script in %TBBS_HOME%\\bin for details");
                    System.exit(1);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ServerConfig sc = new ServerConfig(args[1]);
        new TBBS(sc).start();
    }
}
