import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

/**
 * Runs the application using an embedded Jetty instance.
 * 
 * @author Thiago H. de Paula Figueiredo
 */
public class Main {

    /**
	 * @param args
	 */
    public static void main(String[] args) {
        Server server = new Server(80);
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setWar("src/main/webapp");
        server.setHandler(webapp);
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
