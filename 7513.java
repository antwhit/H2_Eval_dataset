import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: form1
 *
 */
public class Form2 extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

    static final long serialVersionUID = 1L;

    public Form2() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String docType = "<!DOCTYPE HTML PUBLIC \" - //W3C//DTD HTML 4.0 " + "Transitional//EN \" > \n";
        out.println(docType + "<HTML>\n" + "<HEAD><TITLE>Hello</TITLE></HEAD>\n" + "<BODY BGCOLOR=\"#FDF5E6\">bioz\n" + request.getParameter("firstName") + request.getParameter("lastName") + "</BODY></HTML>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
