import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class GestorFacturacionSandwich {

    private GestorDisco gd;

    Connection con = null;

    public GestorFacturacionSandwich() throws errorConexionBD {
        super();
        gd = new GestorDisco();
        gd.abrirConexion();
    }

    public void insertarFacturacionSandwich(int idProducto) throws errorSQL, errorConexionBD {
        if (gd.isConectado()) con = gd.getConexion(); else throw new errorConexionBD("No hay conexion!");
    }

    public void eliminaFacturacionSandwich(int idProducto) throws errorSQL, errorConexionBD {
        if (gd.isConectado()) con = gd.getConexion(); else throw new errorConexionBD("No hay conexion!");
        Producto p = null;
        Facturacion f = null;
        String pr;
        PreparedStatement pstmt = null;
        try {
            gd.begin();
            pr = "DELETE FROM factura(importe) WHERE p.id=f.id and p.id = '" + idProducto + "'";
            gd.commit();
            pstmt.execute();
            pstmt.close();
        } catch (SQLException e) {
            gd.rollback();
            throw new errorSQL(e.toString());
        }
    }

    public FacturacionSandwich consultaFacturacionSandwich(int idProducto) throws errorSQL, errorConexionBD {
        FacturacionSandwich fs = null;
        Producto p = null;
        if (gd.isConectado()) con = gd.getConexion(); else throw new errorConexionBD("No hay conexion!");
        Statement stmt = null;
        int id = 0;
        try {
            stmt = con.createStatement();
            String consulta = "SELECT from Sandwich(precio) WHERE p.id=" + idProducto + "'";
            ResultSet rs = stmt.executeQuery(consulta);
            if (rs.next()) id = rs.getInt(1);
            rs.close();
            stmt.close();
            fs = new FacturacionSandwich(rs.getInt(1));
        } catch (SQLException e) {
            throw new errorSQL(e.toString());
        }
        return fs;
    }

    public void liberarRecursos() {
        gd.cerrarConexion();
    }
}
