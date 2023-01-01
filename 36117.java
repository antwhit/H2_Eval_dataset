import pspdash.*;
import pspdash.data.DataRepository;
import java.io.IOException;
import java.util.Enumeration;

public class dts extends TinyCGIBase {

    /** Generate CGI script output. */
    protected void writeContents() {
        DefectTypeStandard defectTypeStandard = DefectTypeStandard.get(getPrefix(), getDataRepository());
        String name = defectTypeStandard.getName();
        String title = (name == null ? "" : " (" + name + ")");
        out.println("<HTML><HEAD>" + cssLinkHTML());
        out.println("<TITLE>Defect Type Standard" + title + "</TITLE>");
        out.println("</HEAD><BODY>");
        out.println("<H1>Defect Type Standard" + title + "</H1>");
        out.println("<TABLE BORDER><TR><TD><B>Type</B></TD>");
        out.println("    <TD><B>Description</B></TD></TR>");
        String type, description;
        for (int i = 0; i < defectTypeStandard.options.size(); i++) {
            type = (String) defectTypeStandard.options.elementAt(i);
            description = (String) defectTypeStandard.comments.get(type);
            if (description == null) description = "&nbsp;";
            out.println("<TR><TD VALIGN=baseline>" + type + "</TD>");
            out.println("    <TD VALIGN=baseline>" + description + "</TD></TR>");
        }
        out.println("</TABLE>");
        if ("PSP - text".equals(name) || "PSP - numbers".equals(name)) out.println(COPYRIGHT_NOTICE);
        out.println("</BODY></HTML>");
    }

    private static final String COPYRIGHT_NOTICE = "<HR><FONT SIZE='-1'><I>Adapted from\n" + "<a href='/help/Topics/Overview/disc-eng.html'>A&nbsp;Discipline\n" + "for Software Engineering</a>, copyright &copy;&nbsp;1995\n" + "Addison-Wesley. Used by permission.</I></FONT>\n";
}
