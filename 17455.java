import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * This is a simple example of an HTTP Servlet.  It responds to the GET
 * method of the HTTP protocol.
 */
public class GreetingServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setBufferSize(8192);
        PrintWriter out = response.getWriter();
        out.println("<html>" + "<head><title>Sample Request and Response</title></head>");
        out.println("<body  bgcolor=\"#ffffff\">" + "<img src=\"duke.waving.gif\" alt=\"Duke waving\">" + "<h2>Hello, my name is Andrique. My hobby is Bird breeding. Tell me about yourself.</h2>" + "<form method=\"get\">" + "What's your name?" + "<input type=\"text\" name=\"username\" size=\"25\">" + "<p></p>" + "Your hobby?" + "<input type=\"text\" name=\"hobby\" size=\"25\">" + "<p></p>" + "What do you enjoy about your hobby?" + "<input type=\"textfield\" name=\"enjoy\" rows=\"3\" cols=\"25\">" + "<p></p>" + "How often do you spend time on your hobby?" + "<p>" + "<input type=\"radio\" name=\"howOften\" value=\"everyday\">" + "Everyday" + "</br>" + "<input type=\"radio\" name=\"howOften\" value=\"twice a week\">" + "Twice a week" + "</br>" + "<input type=\"radio\" name=\"howOften\" value=\"once a week\">" + "Once a week" + "</br>" + "</p>" + "<p>Please enter more information about yourself!</p>" + "<textarea rows=\"3\" cols=\"30\" name=\"address\">Enter your address here</textarea>" + "<input type=\"submit\" value=\"Submit\">" + "<input type=\"reset\" value=\"Reset\">" + "</form>");
        String username = request.getParameter("username");
        if ((username != null) && (username.length() > 0)) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ResponseServlet");
            if (dispatcher != null) {
                dispatcher.include(request, response);
            }
        }
        out.println("</body></html>");
        out.close();
    }

    public String getServletInfo() {
        return "The Hello servlet says hello.";
    }
}
