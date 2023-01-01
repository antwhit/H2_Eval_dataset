import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The simplest possible servlet.
 *
 * @author James Duncan Davidson
 */
public class HelloWorldExample extends HttpServlet {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        String title = "This is sample page";
        out.println("<title>" + title + "</title>");
        out.println("</head>");
        out.println("<body bgcolor=\"white\">");
        out.println("Hi Sevelet is ok <br>");
        out.println("home: " + System.getProperties().toString() + "<br>");
        Map map = System.getenv();
        Set entries = map.entrySet();
        Set keys = map.keySet();
        Iterator i = keys.iterator();
        Iterator i2 = entries.iterator();
        while (i.hasNext()) {
            out.println("env: key " + i.next() + "<br>");
            out.println("env: entris " + i2.next() + "<br>");
        }
        out.println("</body>");
        out.println("</html>");
        System.out.println("Yes out");
    }
}
