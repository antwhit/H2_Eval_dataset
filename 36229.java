import pspdash.*;
import java.io.IOException;

public class showTaskSchedule extends TinyCGIBase {

    /** Write the CGI header. */
    protected void writeHeader() {
        out.print("Expires: 0\r\n");
        super.writeHeader();
    }

    /** Generate CGI script output. */
    protected void writeContents() throws IOException {
        DashController.checkIP(env.get("REMOTE_ADDR"));
        DashController.showTaskSchedule(getPrefix());
        DashController.printNullDocument(out);
    }
}
