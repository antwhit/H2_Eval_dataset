import pspdash.*;
import java.io.IOException;

public class raiseWindow extends TinyCGIBase {

    /** Generate CGI script output. */
    protected void writeContents() throws IOException {
        DashController.checkIP(env.get("REMOTE_ADDR"));
        DashController.raiseWindow();
        DashController.printNullDocument(out);
    }
}
