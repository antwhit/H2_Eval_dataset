import com.trilead.ssh2.Connection;
import com.trilead.ssh2.Session;
import com.trilead.ssh2.StreamGobbler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * This is a very basic example that shows
 * how one can login to a machine and execute a command.
 *  
 * @author Christian Plattner, plattner@trilead.com
 * @version $Id: Basic.java,v 1.4 2007/10/15 12:49:57 cplattne Exp $
 */
public class Basic {

    public static void main(String[] args) {
        String hostname = "127.0.0.1";
        String username = "joe";
        String password = "joespass";
        try {
            Connection conn = new Connection(hostname);
            conn.connect();
            boolean isAuthenticated = conn.authenticateWithPassword(username, password);
            if (isAuthenticated == false) throw new IOException("Authentication failed.");
            Session sess = conn.openSession();
            sess.execCommand("uname -a && date && uptime && who");
            System.out.println("Here is some information about the remote host:");
            InputStream stdout = new StreamGobbler(sess.getStdout());
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
            while (true) {
                String line = br.readLine();
                if (line == null) break;
                System.out.println(line);
            }
            System.out.println("ExitCode: " + sess.getExitStatus());
            sess.close();
            conn.close();
        } catch (IOException e) {
            e.printStackTrace(System.err);
            System.exit(2);
        }
    }
}
