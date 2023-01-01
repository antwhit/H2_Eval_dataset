import pspdash.*;
import java.io.IOException;
import java.util.Vector;

public class dumpData extends TinyCGIBase {

    protected void writeHeader() {
        out.print("Content-type: text/plain\r\n\r\n");
    }

    /** Generate CGI script output. */
    protected void writeContents() throws IOException {
        DashController.checkIP(env.get("REMOTE_ADDR"));
        Vector filter = null;
        String prefix = getPrefix();
        if (prefix != null && prefix.length() > 1) {
            filter = new Vector();
            filter.add(prefix);
        }
        getDataRepository().dumpRepository(out, filter);
    }
}
