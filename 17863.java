import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import atualizare.logica.*;
import atualizare.dados.*;
import atualizare.tools.*;

public class ComentarNoticia extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        RequestDispatcher dispatcher = null;
        if (usuario != null && usuario.getPermissao().equals(GerenteDeUsuarios.PERMISSAO_EDITOR)) {
            String codigo = request.getParameter("codigo");
            int codigoNoticia = 0;
            try {
                codigoNoticia = Integer.parseInt(codigo);
            } catch (Exception e) {
                System.out.println("Erro no ComentarNoticia(1):" + e.getMessage());
            }
            if (codigoNoticia > 0) {
                Comentario comentario = new Comentario();
                comentario.setNoticia(codigoNoticia);
                String temp = null;
                comentario.setTitulo(request.getParameter("comentarioTitulo"));
                comentario.setFonte(request.getParameter("comentarioFonte"));
                comentario.setSubtitulo(request.getParameter("comentarioSubtitulo"));
                comentario.setChamada(request.getParameter("comentarioChamada"));
                temp = request.getParameter("comentarioImagemChamada");
                if (temp == null) temp = "";
                comentario.setFiguraChamada(temp);
                temp = request.getParameter("comentarioCreditoChamada");
                if (temp == null) temp = "";
                comentario.setCreditoChamada(temp);
                temp = request.getParameter("comentarioLegendaChamada");
                if (temp == null) temp = "";
                comentario.setLegendaChamada(temp);
                comentario.setCorpo(request.getParameter("comentarioCorpo"));
                temp = request.getParameter("comentarioImagemCorpo");
                if (temp == null) temp = "";
                comentario.setFiguraCorpo(temp);
                temp = request.getParameter("comentarioCreditoCorpo");
                if (temp == null) temp = "";
                comentario.setCreditoCorpo(temp);
                temp = request.getParameter("comentarioLegendaCorpo");
                if (temp == null) temp = "";
                comentario.setLegendaCorpo(temp);
                temp = request.getParameter("dataHoraPublicacao");
                if (temp == null) temp = "";
                comentario.setDataPublicacao(temp);
                temp = request.getParameter("dataHoraExpiracao");
                if (temp == null) temp = "";
                comentario.setDataSaida(temp);
                if (GerenteDeNoticias.comentarNoticia(comentario)) {
                    try {
                        dispatcher = getServletContext().getRequestDispatcher("/atualizare/mensagem.jsp?msg=A noticia foi comentada com sucesso!!");
                        dispatcher.forward(request, response);
                    } catch (Exception e) {
                        System.out.println("Erro no ComentarNoticia(2):" + e.getMessage());
                    }
                } else {
                    try {
                        dispatcher = getServletContext().getRequestDispatcher("/atualizare/erro.jsp?erro=Erro ao comentar a noticia!!");
                        dispatcher.forward(request, response);
                    } catch (Exception e) {
                        System.out.println("Erro no ComentarNoticia(3):" + e.getMessage());
                    }
                }
            } else {
                try {
                    dispatcher = getServletContext().getRequestDispatcher("/atualizare/erro.jsp?erro=Erro ao comentar a noticia!!");
                    dispatcher.forward(request, response);
                } catch (Exception e) {
                    System.out.println("Erro no ComentarNoticia(4):" + e.getMessage());
                }
            }
        }
    }
}
