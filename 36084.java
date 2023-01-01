import java.sql.Connection;

public class GestorUsuario {

    GestorDisco gd;

    Connection con = null;

    public GestorUsuario() throws errorConexionBD {
        super();
        gd = new GestorDisco();
        gd.abrirConexion();
    }

    public boolean login(String codUsuario, String password, boolean isCliente) throws errorConexionBD, errorSQL {
        Usuario rUsu = null;
        GestorEmpleado gEmp = null;
        boolean ret = false;
        int pId = 0;
        pId = Util.getNumDeCodigo(codUsuario);
        if (isCliente == false) {
            gEmp = new GestorEmpleado();
            rUsu = gEmp.consultaEmpleado(pId);
            if (!rUsu.getPassword().equals(password)) {
                throw new GestorUsuarioException();
            }
            if (!rUsu.getPassword().equals(password)) {
                throw new GestorUsuarioException();
            }
            if (rUsu.getFechaBaja() != null) {
                throw new GestorUsuarioException("Usuario dado de baja");
            }
            if (rUsu.isDesactivado()) {
                throw new GestorUsuarioException("Usuario desactivado");
            }
            ret = true;
        } else {
        }
        return ret;
    }

    public void liberarRecursos() {
        gd.cerrarConexion();
    }

    public static void main(String[] args) {
        try {
            GestorUsuario gUsu = new GestorUsuario();
            try {
                gUsu.login("C0001", "prueba", false);
            } catch (errorSQL e) {
                System.out.println(e.getMessage());
            }
            gUsu.liberarRecursos();
        } catch (errorConexionBD e) {
            System.out.println(e.getMessage());
        }
    }
}
