import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class GestorRol {

    GestorDisco gd;

    Connection con = null;

    public GestorRol() throws errorConexionBD {
        super();
        gd = new GestorDisco();
        gd.abrirConexion();
    }

    public void addRol(Rol pRol) throws errorSQL, errorConexionBD {
        String strSQL = "";
        verifCampos(pRol);
        if (gd.isConectado()) con = gd.getConexion(); else throw new errorConexionBD("No hay conexi�n!");
        PreparedStatement pstmt = null;
        try {
            gd.begin();
            strSQL = "INSERT INTO rol (descripcion,letra_rol) " + "VALUES (?,?)";
            pstmt = con.prepareStatement(strSQL);
            pstmt.setString(1, pRol.getDescripcion());
            pstmt.setString(2, pRol.getLetraRol());
            pstmt.execute();
            pstmt.close();
            pstmt = null;
            gd.commit();
        } catch (SQLException e) {
            gd.rollback();
            throw new errorSQL("Error SQL numero: " + e.getErrorCode());
        }
    }

    public Rol consultaRol(int pId) throws errorSQL, errorConexionBD {
        String strSQL = "";
        Rol pRol = null;
        if (gd.isConectado()) con = gd.getConexion(); else throw new errorConexionBD("No hay conexi�n!");
        Statement stmt = null;
        try {
            gd.begin();
            strSQL = "SELECT id, descripcion, letra_Rol " + "FROM rol " + "WHERE id = " + pId;
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(strSQL);
            if (rs.next()) {
                pRol = montaRol(rs);
            } else {
                throw new GestorRolException("No existe rol");
            }
            rs.close();
            stmt.close();
            gd.commit();
            return pRol;
        } catch (SQLException e) {
            throw new GestorRolException("Error SQL numero: " + e.getErrorCode());
        }
    }

    public Vector lista() throws errorSQL, errorConexionBD {
        Vector v = new Vector();
        String strSQL = "";
        strSQL = "SELECT id, descripcion, letra_Rol " + "FROM rol";
        Rol pRol = null;
        if (gd.isConectado()) con = gd.getConexion(); else throw new errorConexionBD("No hay conexion!");
        Statement stmt = null;
        ResultSet rs = null;
        try {
            gd.begin();
            stmt = con.createStatement();
            rs = stmt.executeQuery(strSQL);
            while (rs.next()) {
                pRol = montaRol(rs);
                v.add(pRol);
            }
            rs.close();
            stmt.close();
            gd.commit();
        } catch (SQLException e) {
            throw new errorSQL(e.toString());
        }
        return v;
    }

    private Rol montaRol(ResultSet rs) throws errorSQL {
        Rol pRol = null;
        pRol = new Rol();
        try {
            pRol.setId(rs.getInt("id"));
            pRol.setDescripcion(rs.getString("descripcion"));
            pRol.setLetraRol(rs.getString("letra_Rol"));
        } catch (SQLException e) {
            throw new errorSQL(e.toString());
        }
        return pRol;
    }

    private void verifCampos(Rol pRol) throws GestorRolException {
        if (!Util.compruebaCampoTamano(pRol.getDescripcion().toCharArray(), 50)) throw new GestorRolException("Error tama�o campo descripci�n");
        if (!Util.compruebaCampoTamano(pRol.getLetraRol().toCharArray(), 1)) throw new GestorRolException("Error tama�o campo letra ROL");
    }

    public void liberarRecursos() {
        gd.cerrarConexion();
    }
}
