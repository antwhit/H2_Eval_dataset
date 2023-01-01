import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class QuickPollSetup extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String q = request.getParameter("question");
        ServletContext c = getServletContext();
        c.setAttribute("question", htmlEscape(q));
        c.setAttribute("yes", new Integer(0));
        c.setAttribute("no", new Integer(0));
        c.setAttribute("askAction", "vote");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("<html><head><title>QuickPoll</title></head><body>" + "<h1>QuickPoll</h1>" + "Your question has been registered. " + "Let the vote begin!" + "</body></html>");
    }

    /**
     * �ndring: 
     *   htmlEscape tager en streng som param og returnerer
     *   samme streng der bare har �ndret de chars html er 
     *   f�lsom overfor til det samme tegn bare uden html 
     *   egenskaberne.
     */
    private String htmlEscape(String s) {
        StringBuffer b = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch(c) {
                case '<':
                    b.append("&lt;");
                    break;
                case '>':
                    b.append("&rt;");
                    break;
                case '"':
                    b.append("&quot;");
                    break;
                case '\'':
                    b.append("&apos;");
                    break;
                case '&':
                    b.append("&amp;");
                    break;
                default:
                    b.append(c);
            }
        }
        return b.toString();
    }
}
