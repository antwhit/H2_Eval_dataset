import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class GestorEjemplo {

    GestorDisco gd;

    Connection con = null;

    public GestorEjemplo() throws errorConexionBD {
        super();
        gd = new GestorDisco();
        gd.abrirConexion();
    }

    public Vector<Ejemplo> listaEjemplos() throws errorSQL, errorConexionBD {
        Vector<Ejemplo> v = new Vector<Ejemplo>();
        Ejemplo ej = null;
        if (gd.isConectado()) con = gd.getConexion(); else throw new errorConexionBD("No hay conexion!");
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * from ejemplo");
            while (rs.next()) {
                ej = new Ejemplo(rs.getString(1));
                v.add(ej);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new errorSQL(e.toString());
        }
        return v;
    }

    public void insertarEjemplo(String ejemplo, int numero) throws errorSQL, errorConexionBD {
        if (gd.isConectado()) con = gd.getConexion(); else throw new errorConexionBD("No hay conexion!");
        PreparedStatement pstmt = null;
        Statement stmt = null;
        int id = 0;
        try {
            gd.begin();
            stmt = con.createStatement();
            String consulta = "SELECT nextval('ejemplo_seq')";
            ResultSet rs = stmt.executeQuery(consulta);
            if (rs.next()) id = rs.getInt(1);
            rs.close();
            stmt.close();
            pstmt = con.prepareStatement("insert into ejemplo values(?,?,?);");
            pstmt.setInt(1, id);
            pstmt.setString(2, ejemplo);
            pstmt.setInt(3, numero);
            gd.commit();
            pstmt.execute();
            pstmt.close();
        } catch (SQLException e) {
            gd.rollback();
            throw new errorSQL(e.toString());
        }
    }

    public void liberarRecursos() {
        gd.cerrarConexion();
    }
}
