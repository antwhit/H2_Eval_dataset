import pspdash.*;
import java.io.IOException;

public class stopTiming extends TinyCGIBase {

    /** Write the CGI header. */
    protected void writeHeader() {
        out.print("Content-type: text/html\r\n");
        out.print("Expires: 0\r\n\r\n");
    }

    /** Generate CGI script output. */
    protected void writeContents() throws IOException {
        DashController.checkIP(env.get("REMOTE_ADDR"));
        DashController.stopTiming();
        DashController.printNullDocument(out);
    }
}
