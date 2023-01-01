import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;

public class Main extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.print("<html><body>");
        out.print("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />");
        out.print("<div id=\"outer\">" + " <div id=\"wrapper\">" + " <div id=\"nav\">" + " <div id=\"nav-left\">" + " <div id=\"nav-right\">");
        out.print("<ul>" + "<li><a href=addItem.jsp>Add New Item</a</li>" + "<li><a href=addCategory.jsp>Add New Category</a></li>" + "<li><a href=listItem>Search</a></li>" + " </ul>");
        out.print("</div>");
        out.print("</div>");
        out.print("<div class=\"clear\"></div>");
        out.print("</div>");
        out.print("<div id=\"head\">" + "<div id=\"head-left\"></div>" + "<div id=\"head-right\"></div>" + "<div id=\"head-1\"></div>" + "<h1><span class=\"logo\"><span class=\"top\">computer</span><span class=\"gadgets\">inventory</span></span></h1>");
        out.print("</div>");
        out.print("<div id=\"head-2\"></div>" + "<div id=\"login\">" + "<div id=\"login-bot\">" + "<div id=\"login-box\">" + "<h2 class=\"login\"><em>user</em>login</h2>" + "<form action=\"http://www.free-css.com/\">" + "<div id=\"login-username\">" + "<div>" + "<label for=\"username\">username</label>" + ":" + "<input type=\"text\" name=\"username\" value=\"\" id=\"username\" />" + "</div>" + "<div>" + "<label for=\"password\">password</label>" + ":" + "<input type=\"password\" name=\"password\" value=\"\" id=\"password\" />" + "</div>" + "</div>" + "<div id=\"login-button\">" + "<input type=\"image\" src=\"images/btn_login.gif\" name=\"l\" value=\"h\" id=\"l\" />" + "</div>" + "<div class=\"clear\">" + "<div class=\"reg\"> New User? <a href=\"http://www.free-css.com/\">REGISTER for FREE</a> </div>" + "</div>" + "</form>" + "</div>" + "<div id=\"login-welcome\">" + "<div>" + "<h2>Welcome</h2>" + "<p>Don't forget to check <a href=\"http://www.free-css.com/\">free website templates</a> every day, because we add a new free website template almost daily.</p>" + "</div>" + "</div>" + "<div class=\"clear\"></div>" + "</div>" + "</div>");
        out.print("</div>" + " </div>");
        out.print("</body></html>");
        out.close();
    }

    public void des() {
        System.out.print("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />");
    }
}
