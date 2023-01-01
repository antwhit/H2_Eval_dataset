public class GestorUsuario {

    GestorEmpleado gEmp;

    GestorCliente gCli;

    public GestorUsuario() throws errorConexionBD {
        super();
        gEmp = new GestorEmpleado();
        gCli = new GestorCliente();
    }

    public Usuario login(String codUsuario, String password, boolean isCliente) throws errorConexionBD, errorSQL {
        Usuario rUsu = null;
        int pId = 0;
        pId = Util.getNumDeCodigo(codUsuario);
        if (isCliente) {
            System.out.println("isCliente=true");
            rUsu = gCli.getCliente(pId);
        } else {
            System.out.println("no es cliente: empleado");
            rUsu = gEmp.consultaEmpleado(pId);
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
        return rUsu;
    }

    public void liberarRecursos() {
        gEmp.liberarRecursos();
        gCli.liberarRecursos();
    }
}
