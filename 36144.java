import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class NoInvokerServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Invoker Servlet Disabled.";
        out.println("<HTML>\n" + "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" + "<BODY BGCOLOR=\"#FDF5E6\">\n" + "<H2>" + title + "</H2>\n" + "Sorry, access to servlets by means of\n" + "URLs that begin with\n" + "http://localhost/Assignment/musicSales.*/\n" + "has been disabled.\n" + "</BODY></HTML>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
