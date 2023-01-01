import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.Enumeration;

public class MudarEditor extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getParameter("path");
        Enumeration parametersNames = request.getParameterNames();
        RequestDispatcher dispatcher = null;
        String parameterName = "";
        String value = "";
        while (parametersNames.hasMoreElements()) {
            parameterName = (String) parametersNames.nextElement();
            value = request.getParameter(parameterName);
            request.setAttribute(parameterName, value);
        }
        String formatacao = request.getParameter("formatacao");
        if (formatacao != null && formatacao.equals("true")) request.setAttribute("formatacao", "false"); else request.setAttribute("formatacao", "true");
        try {
            dispatcher = getServletContext().getRequestDispatcher(path);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            System.out.println("Erro no MudarEditor.java:" + e.toString());
        }
    }
}
