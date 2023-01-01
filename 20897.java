import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This servlet implements functionality for editing and deleting customer information. It simply
 * removes a specific reservation from the database and refreshes the current user page.
 * @author knunweiler
 *
 */
public class deleteReservationServlet extends HttpServlet {

    private static final long serialVersionUID = -8431430892643244256L;

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String path = req.getServletPath();
        String reserveid = req.getParameter("rid").toString();
        DBAccess.deleteReservation(new Integer(reserveid));
        res.sendRedirect("restViewReservations.html");
    }
}
