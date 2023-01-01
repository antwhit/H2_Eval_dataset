import java.io.IOException;
import pspdash.DashController;
import pspdash.PSPDashboard;
import pspdash.TinyCGIBase;

public class showenv extends TinyCGIBase {

    /** Generate CGI script output. */
    protected void writeContents() throws IOException {
        if (parameters.get("config") != null) {
            out.print(DashController.getSettingsFileName());
            return;
        }
        out.println("<HTML><HEAD><TITLE>User settings</TITLE></HEAD>");
        out.println("<BODY><H1>Your settings</H1>");
        out.print("<P>Your configuration file is:<PRE>     ");
        out.println(DashController.getSettingsFileName());
        out.println("</PRE></P>");
        out.print("<P>Your data is located in the directory:<PRE>     ");
        out.println(PSPDashboard.getDefaultDirectory());
        out.println("</PRE></P>");
        out.println("</BODY></HTML>");
    }
}
