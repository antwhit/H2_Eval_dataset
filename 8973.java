import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import atualizare.logica.*;
import atualizare.dados.*;

public class RemoverMateriaColunista extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null && usuario.getPermissao().equals(GerenteDeUsuarios.PERMISSAO_COLUNISTA)) {
            RequestDispatcher dispatcher = null;
            String codigoMateriaParam = request.getParameter("codigo");
            int codigoMateria = Integer.parseInt(codigoMateriaParam);
            if (GerenteDeColunas.removerMateriaColunista(codigoMateria)) {
                dispatcher = getServletContext().getRequestDispatcher("/jsp/principal.jsp?msg=Materia removida com sucesso!!!");
                dispatcher.forward(request, response);
            } else {
                dispatcher = getServletContext().getRequestDispatcher("/atualizare/jsp/principal.jsp?msg=Nao foi possivel remover a Materia!!!");
                dispatcher.forward(request, response);
            }
        }
    }
}
