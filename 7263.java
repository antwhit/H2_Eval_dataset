import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AvaliaAluno {

    /**
	 * @param args
	 * @throws SQLException 
	 */
    public static void main(String[] args) throws SQLException {
        Avaliador aval = new Avaliador();
        aval.conectaBanco();
        aval.setInst(2);
        aval.setTipoCurso(1);
        aval.setCurso(4);
        aval.setDisc("EN05064");
        aval.setTurma("100010");
        aval.setCdUsuarioAluno("02100000901");
        aval.setCdConteudo(80);
        aval.setProblema(67);
        aval.setCdSolAluno(67);
        aval.setAval(1);
        aval.setCdResposta(13);
        aval.setCdUsuario("1152675");
        aval.setCdSol(1);
        aval.atualizaNotaAluno();
    }
}
