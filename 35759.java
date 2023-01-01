import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;
import model.JOFSModel;
import model.exception.NotExistentException;

/**
 *
 * @author rayman
 */
public class AddClass extends HttpServlet {

    /** 
    * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String individual = (String) request.getParameter("individual");
            String className = (String) request.getParameter("className");
            if (individual == null || className == null) {
                request.setAttribute("error", "Errore, manca un parametro!");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/error.jsp");
                rd.forward(request, response);
            }
            JOFSModel jofs = new JOFSModel("prove_jofs");
            jofs.submitFileClassification(individual, className);
            request.setAttribute("individual", individual);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/OntIndividual");
            rd.forward(request, response);
        } catch (NotExistentException ex) {
            request.setAttribute("error", ex.getError());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
        }
    }

    /** 
    * Handles the HTTP <code>GET</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
    * Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
    * Returns a short description of the servlet.
    */
    public String getServletInfo() {
        return "Short description";
    }
}
