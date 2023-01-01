import java.io.*;
import java.sql.*;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;

public class AddItem extends HttpServlet {

    private String url, sql;

    private Connection conn;

    private PreparedStatement ps;

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.print("<html><body>");
        out.print("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />");
        out.print("<div id=\"outer\">" + " <div id=\"wrapper\">" + " <div id=\"nav\">" + " <div id=\"nav-left\">" + " <div id=\"nav-right\">");
        out.print("<ul>" + "<li><a href=\"http://localhost:8080/ForFinals/AddItem\">Add New Item</a</li>" + "<li><a href=\"http://localhost:8080/ForFinals/AddCategory\">Add New Category</a></li>" + "<li><a href=\"http://localhost:8080/ForFinals/SearchItem\">Search</a></li>" + " </ul>");
        out.print("</div>");
        out.print("</div>");
        out.print("<div class=\"clear\"></div>");
        out.print("</div>");
        out.print("<div id=\"head-2\"></div>" + "<div id=\"login\">" + "<div id=\"login-bot\">");
        out.print("<form action=\"");
        out.print(req.getRequestURI());
        out.print("\" method=\"post\">");
        out.print("Item Code :");
        out.print("<input type=\"text\" name=\"itemCode\"><br>");
        out.print("Item Name :");
        out.print("<input type=\"text\" name=\"itemName\"><br>");
        out.print("Item Detail :");
        out.print("<input type=\"text\" name=\"itemDetail\"><br>");
        out.print("Category Code :");
        out.print("<input type=\"text\" name=\"categoryCode\"><br>");
        out.print("Quantity :");
        out.print("<input type=\"text\" name=\"itemQuantity\"><br>");
        out.print("<br><br><input type=\"submit\" value=\"Add Item\">");
        out.print("<input type=\"reset\" value=\"Clear\">");
        out.print("</div>" + " </div>");
        out.print("</body></html>");
        out.close();
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.print("<html><body>");
        String iCode = req.getParameter("itemCode").trim();
        String iName = req.getParameter("itemName").trim();
        String iDetail = req.getParameter("itemDetail").trim();
        String cCode = req.getParameter("categoryCode").trim();
        String iQuantity = req.getParameter("itemQuantity").trim();
        boolean proceed = false;
        if (iCode != null && iName != null && iDetail != null && cCode != null && iQuantity != null) if (iCode.length() > 0 && iName.length() > 0 && iDetail != null && cCode != null && iQuantity != null) proceed = true;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            url = "jdbc:mysql://localhost/inventory";
            conn = DriverManager.getConnection(url, "root", "");
            sql = "INSERT INTO item(item_code, item_name, item_detail, category_code, quantity) VALUES(?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            if (proceed) {
                ps.setString(1, iCode);
                ps.setString(2, iName);
                ps.setString(3, iDetail);
                ps.setString(4, cCode);
                ps.setString(5, iQuantity);
                ps.executeUpdate();
            }
            out.print("You have added ");
            out.print(iQuantity);
            out.print(" ");
            out.print(iName);
        } catch (ClassNotFoundException cnfe) {
            out.println("" + cnfe);
        } catch (SQLException sqle) {
            out.println("" + sqle);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException sqle) {
                out.println("" + sqle);
            }
        }
        out.print("</body></html>");
        out.close();
    }
}
