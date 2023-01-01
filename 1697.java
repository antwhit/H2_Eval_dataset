import pspdash.*;
import java.io.IOException;

/** CGI script to print out the DNS name of the web server.
 */
public class serverName extends TinyCGIBase {

    protected void writeContents() throws IOException {
        out.print(TinyWebServer.getHostName());
        out.flush();
    }
}
