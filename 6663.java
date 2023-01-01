import isg3.operations.*;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: NewName
 *
 */
public class NewName extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

    static final long serialVersionUID = 1L;

    public NewName() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String artName = request.getParameter("id");
        IWikiOperations op = new WikiOperations();
        RequestDispatcher d = null;
        if (op.existsArticle(artName)) {
            d = request.getRequestDispatcher("./FrontController?res=A7e");
        } else {
            d = request.getRequestDispatcher("./FrontController?res=A4e");
        }
        if (d != null) {
            d.forward(request, response);
        }
    }
}
