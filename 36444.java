import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.LoginDB;

public class NewAccountServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws java.io.IOException, ServletException {
        LoginDB loginDB = (LoginDB) getServletContext().getAttribute("loginDB");
        loginDB.addUser(req.getParameter("userName"), req.getParameter("password"), req.getParameter("password-hint"));
        getServletContext().getRequestDispatcher(res.encodeURL("/accountCreated.jsp")).forward(req, res);
    }
}
