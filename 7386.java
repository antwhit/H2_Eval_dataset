import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A Servlet to help view jpg images
 *
 * @author James Chy
 */
public class TestPost extends HttpServlet {

    static final long serialVersionUID = 0;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body bgcolor=\"white\">");
        out.println("<head>");
        out.println("<title>Test Post</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<FORM ACTION=TestPost METHOD=POST>");
        out.println("<INPUT TYPE=IMAGE SRC=dummy.gif NAME=PREVIOUS VALUE=Previous>");
        out.println("<INPUT TYPE=IMAGE SRC=dummy.gif NAME=NEXT VALUE=Next>");
        out.println("<INPUT TYPE=SUBMIT NAME=SUBMIT VALUE=Submit>");
        out.println("</FORM>");
        out.println("</body>");
        out.println("</html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body bgcolor=\"white\">");
        out.println("<head>");
        out.println("<title>Post</title>");
        out.println("</head>");
        out.println("<body>");
        for (Enumeration e = request.getParameterNames(); e.hasMoreElements(); ) {
            String currentElement = e.nextElement().toString();
            String currentValues[] = request.getParameterValues(currentElement);
            for (int i = 0; i < currentValues.length; i++) out.println(currentElement + "[" + i + "]=" + currentValues[i] + "<BR>");
        }
        out.println("</BODY></HTML>");
    }
}
