import pspdash.*;
import pspdash.TimeLog;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.Enumeration;

public class timecard extends TinyCGIBase {

    /** Write a standard CGI header.
     */
    protected void writeHeader() {
        out.print("Content-type: application/vnd.ms-excel\r\n\r\n");
    }

    private static final String HEADER_START = "<HTML><HEAD><TITLE>Excel Time Card</TITLE>\n" + "<TABLE BORDER CROSSTAB><TR>\n" + "<TH PAGEFIELD>Month</TH>" + "<TH COLFIELD>Day</TH>" + "<TH DATAFIELD AGGREGATOR=\"SUM\">Time</TH>";

    private static final String HEADER_MIDA = "<TH ROWFIELD>Hier";

    private static final String HEADER_MIDB = "</TH>";

    private static final String HEADER_END = "</TR>\n";

    private static SimpleDateFormat MONTH = null;

    private static SimpleDateFormat DAY = null;

    static {
        try {
            MONTH = new SimpleDateFormat("yyyy-MMM");
            MONTH.setTimeZone(TimeZone.getDefault());
            DAY = new SimpleDateFormat("d");
            DAY.setTimeZone(TimeZone.getDefault());
        } catch (Throwable t) {
            t.printStackTrace(System.err);
        }
    }

    /** Generate CGI script output. */
    protected void writeContents() throws IOException {
        System.out.println("getting time log");
        TimeLog tl = new TimeLog();
        tl.readDefault();
        System.out.println("scanning for depth");
        Enumeration rows = tl.filter(PropertyKey.ROOT, null, null);
        TimeLogEntry tle;
        String entryPath;
        int depth = 1, currDepth, i;
        while (rows.hasMoreElements()) {
            tle = (TimeLogEntry) rows.nextElement();
            entryPath = tle.getPath();
            System.out.println(entryPath);
            currDepth = countSlashes(entryPath);
            if (currDepth > depth) depth = currDepth;
        }
        System.out.println("depth is " + depth);
        out.print(HEADER_START);
        for (i = 0; i++ < depth; ) out.print(HEADER_MIDA + i + HEADER_MIDB);
        out.print(HEADER_END);
        System.out.println("scanning to print");
        rows = tl.filter(PropertyKey.ROOT, null, null);
        StringTokenizer tok;
        while (rows.hasMoreElements()) {
            tle = (TimeLogEntry) rows.nextElement();
            out.println("<TR>");
            printCell(MONTH.format(tle.getStartTime()));
            printCell(DAY.format(tle.getStartTime()));
            printCell(Long.toString(tle.getElapsedTime()));
            tok = new StringTokenizer(tle.getPath(), "/");
            for (i = depth; i-- > 0; ) if (tok.hasMoreTokens()) printCell(tok.nextToken()); else printCell("&nbsp;");
            out.println("</TR>");
        }
        out.println("</TABLE>");
        out.println("</BODY></HTML>");
    }

    private int countSlashes(String path) {
        return (new StringTokenizer(path, "/")).countTokens();
    }

    private void printCell(String contents) {
        out.print("<TD>");
        out.print(contents);
        out.println("</TD>");
    }
}
