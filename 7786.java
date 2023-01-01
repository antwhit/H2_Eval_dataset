import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class TPCW_promotional_processing {

    public static void DisplayPromotions(PrintWriter out, HttpServletRequest req, HttpServletResponse res, int new_sid) {
        int I_ID = TPCW_Util.getRandomI_ID();
        Vector related_item_ids = new Vector();
        Vector thumbnails = new Vector();
        int i;
        String url;
        TPCW_Database.getRelated(I_ID, related_item_ids, thumbnails);
        String C_ID = req.getParameter("C_ID");
        String SHOPPING_ID = req.getParameter("SHOPPING_ID");
        out.print("<TABLE ALIGN=CENTER BORDER=0 WIDTH=660>\n");
        out.print("<TR ALIGN=CENTER VALIGN=top>\n");
        out.print("<TD COLSPAN=5><B><FONT COLOR=#ff0000 SIZE=+1>" + "Click on one of our latest books to find out more!" + "</FONT></B></TD></TR>\n");
        out.print("<TR ALIGN=CENTER VALIGN=top>\n");
        for (i = 0; i < related_item_ids.size(); i++) {
            url = "../servlet/TPCW_product_detail_servlet";
            url = url + "?I_ID=" + String.valueOf(related_item_ids.elementAt(i));
            if (SHOPPING_ID != null) url = url + "&SHOPPING_ID=" + SHOPPING_ID; else if (new_sid != -1) url = url + "&SHOPPING_ID=" + new_sid;
            if (C_ID != null) url = url + "&C_ID=" + C_ID;
            out.print("<TD><A HREF=\"" + res.encodeUrl(url));
            out.print("\"><IMG SRC=\"../tpcw/Images/" + thumbnails.elementAt(i) + "\" ALT=\"Book " + String.valueOf(i + 1) + "\" WIDTH=\"100\" HEIGHT=\"150\"></A>\n");
            out.print("</TD>");
        }
        out.print("</TR></TABLE>\n");
    }
}
