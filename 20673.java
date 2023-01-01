import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

public class GestorIngredienteSandwich {

    GestorDisco gd;

    Connection con = null;

    public GestorIngredienteSandwich() throws errorConexionBD {
        super();
        gd = new GestorDisco();
        gd.abrirConexion();
    }

    public IngredienteSandwich lee(int idSandwich, int idIngrediente) {
        return null;
    }

    public void guarda(IngredienteSandwich ingredienteSandwich) throws errorConexionBD {
        if (gd.isConectado()) con = gd.getConexion(); else throw new errorConexionBD("No hay conexion!");
        PreparedStatement ps = null;
        String sql = "";
        sql = "INSERT INTO ingrediente VALUES (?,?,?);";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, ingredienteSandwich.getIdSandwich());
            ps.setInt(2, ingredienteSandwich.getIdIngrediente());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void elimina(IngredienteSandwich ingredienteSandwich) throws errorConexionBD {
        if (gd.isConectado()) con = gd.getConexion(); else throw new errorConexionBD("No hay conexion!");
        Statement st = null;
        String sql = "";
        sql = "DELETE FROM ingrediente WHERE id-producto=" + ingredienteSandwich.getIdSandwich() + " AND id-ingrediente=" + ingredienteSandwich.getIdIngrediente() + ";";
        try {
            st = con.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Collection listaPorSandwich() {
        return null;
    }
}
