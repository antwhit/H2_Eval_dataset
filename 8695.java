import java.io.IOException;
import pspdash.Resources;
import pspdash.Translator;
import pspdash.data.DataRepository;
import pspdash.data.ResultSet;

public class table extends pspdash.TinyCGIBase {

    protected void writeContents() throws IOException {
        String title = tr((String) parameters.get("title"));
        String head = tr((String) parameters.get("headerComment"));
        String foot = tr((String) parameters.get("footerComment"));
        boolean skipRowHdr = (parameters.get("skipRowHdr") != null);
        boolean skipColHdr = (parameters.get("skipColHdr") != null);
        boolean includable = (parameters.get("includable") != null);
        if (!includable) {
            out.println("<HTML><HEAD>");
            if (title != null) out.println("<TITLE>" + title + "</TITLE>");
            out.println(cssLinkHTML());
            out.println("</HEAD><BODY>");
            if (title != null) out.println("<H1>" + title + "</H1>");
            if (head != null) out.println("<P>" + head + "</P>");
            out.println(parameters.containsKey("style") ? "<TABLE>" : "<TABLE BORDER>");
        }
        if (parameters.get("h0") == null) parameters.put("h0", "Project/Task");
        ResultSet tableData = ResultSet.get(getDataRepository(), parameters, getPrefix(), getPSPProperties());
        if (parameters.get("transpose") != null) tableData = tableData.transpose();
        int firstRow = (skipColHdr ? 1 : 0);
        int firstCol = (skipRowHdr ? 1 : 0);
        for (int row = firstRow; row <= tableData.numRows(); row++) {
            out.println("<TR>");
            for (int col = firstCol; col <= tableData.numCols(); col++) {
                out.print("<TD" + getColAttributes(col) + ">");
                out.print(tableData.format(row, col));
                out.println("</TD>");
            }
            out.println("</TR>");
        }
        if (!includable) {
            out.println("</TABLE>");
            if (foot != null) out.println("<P>" + foot + "</P>");
            out.print("<P class='doNotPrint'><A HREF=\"excel.iqy\">" + "<I>Export to Excel</I></A></P></BODY></HTML>");
        }
    }

    private String getColAttributes(int col) {
        String cssClass = (String) parameters.get("c" + col);
        if (cssClass == null) cssClass = (String) parameters.get("c");
        if (cssClass == null) return "";
        return " class='" + cssClass + "'";
    }

    protected String tr(String s) {
        return Translator.translate(s);
    }
}
