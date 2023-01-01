import org.dcm4che.server.*;
import java.util.Date;

/**
 *
 * @author  gunter
 */
public class Syslogd {

    private static final ServerFactory sf = ServerFactory.getInstance();

    private static final String USAGE = "java -jar syslogd.jar port [out-file]";

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println(USAGE);
        }
        Syslogd syslogd = new Syslogd();
        syslogd.setPort(Integer.parseInt(args[0]));
        syslogd.start();
    }

    private SyslogService service = new SyslogService() {

        public void process(Date date, String host, String content) {
            System.out.println("date:" + date + "\nhost:" + host + "\ncontent:" + content);
        }
    };

    private UDPServer server = sf.newUDPServer(sf.newSyslogHandler(service));

    public int getPort() {
        return server.getPort();
    }

    public void setPort(int port) {
        server.setPort(port);
    }

    public void start() throws Exception {
        server.start();
    }
}
