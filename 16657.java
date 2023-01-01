import pspdash.*;
import pspdash.TimeLog;
import java.io.IOException;
import java.util.Enumeration;

public class timelog extends TinyCGIBase {

    private static final String START_TEXT = "<HTML><HEAD><TITLE>Time Log%for owner%%for path%</TITLE>%css%\n" + "<STYLE>\n" + "    TABLE { empty-cells: show }\n" + "    .header { font-weight: bold }\n" + "    TD { vertical-align: baseline }\n" + "</STYLE></HEAD>\n" + "<BODY><H1>Time Log%for path%</H1>\n" + "<TABLE BORDER><TR class=header>\n" + "<TD>Project/Task</TD>\n" + "<TD>Phase</TD>\n" + "<TD>Start Time</TD>\n" + "<TD>Elapsed</TD>\n" + "<TD>Interrupt</TD>\n" + "<TD>Comment</TD></TR>\n";

    private static final String DISCLAIMER = "<P class=doNotPrint><A HREF=\"excel.iqy\"><I>Export to" + " Excel</I></A></P>" + "<P class=doNotPrint><I>This view of the time log is read-only. To " + "add entries to the time log, use the play/pause button on the " + "dashboard. To edit or delete time log entries, use the time log " + "editor (accessible from the Configuration menu of the " + "dashboard).</I></P>";

    /** Generate CGI script output. */
    protected void writeContents() throws IOException {
        String path = getPrefix();
        String title = For(path);
        String owner = For(getOwner());
        String header = START_TEXT;
        header = StringUtils.findAndReplace(header, "%for owner%", owner);
        header = StringUtils.findAndReplace(header, "%for path%", title);
        header = StringUtils.findAndReplace(header, "%css%", cssLinkHTML());
        out.print(header);
        TimeLog tl = new TimeLog();
        tl.readDefault();
        PSPProperties props = getPSPProperties();
        Enumeration rows = tl.filter(props.findExistingKey(path), null, null);
        TimeLogEntry tle;
        String entryPath, phase;
        int slashPos;
        while (rows.hasMoreElements()) {
            tle = (TimeLogEntry) rows.nextElement();
            entryPath = tle.getPath();
            slashPos = entryPath.lastIndexOf("/");
            phase = entryPath.substring(slashPos + 1);
            entryPath = entryPath.substring(0, slashPos);
            out.println("<TR>");
            out.println("<TD NOWRAP>" + entryPath + "</TD>");
            out.println("<TD>" + phase + "</TD>");
            out.println("<TD>" + DateFormatter.formatDateTime(tle.getStartTime()) + "</TD>");
            out.println("<TD>" + tle.getElapsedTime() + "</TD>");
            out.println("<TD>" + tle.getInterruptTime() + "</TD>");
            String comment = tle.getComment();
            out.println("<TD>" + (comment == null ? "" : comment) + "</TD>");
            out.println("</TR>");
        }
        out.println("</TABLE>");
        if (parameters.get("skipFooter") == null) out.print(DISCLAIMER);
        out.println("</BODY></HTML>");
    }

    private String For(String phrase) {
        if (phrase != null && phrase.length() > 1) return " for " + phrase; else return "";
    }
}
