import javax.servlet.http.*;

public class servletToJsp extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("servletName", "servletToJsp");
            getServletConfig().getServletContext().getRequestDispatcher("/jsp/jsptoserv/hello.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
