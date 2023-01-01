import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;

public class ServiciosCocinaImpl extends UnicastRemoteObject implements ServiciosRemotosCocina {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    protected ServiciosCocinaImpl() throws RemoteException {
        super();
    }

    public void desactivarRecursos() {
    }

    public boolean altaSandwich(Sandwich sandwich) throws errorConexionBD {
        GestorSandwich gs = new GestorSandwich();
        gs.guarda(sandwich);
        return true;
    }

    public boolean altaTipoSandwich(TipoSandwich tipoSandwich) throws errorConexionBD {
        GestorTipoSandwich gts = new GestorTipoSandwich();
        gts.guarda(tipoSandwich);
        return true;
    }

    public Sandwich consultaSandwich(int idSandwich) throws errorConexionBD {
        GestorSandwich gs = new GestorSandwich();
        return gs.lee(idSandwich);
    }

    public boolean eliminaSandwich(int idSandwich) throws errorConexionBD {
        GestorSandwich gs = new GestorSandwich();
        gs.elimina(gs.lee(idSandwich));
        return true;
    }

    public Collection filtraTipoSandwich(String nombre) throws errorConexionBD {
        GestorTipoSandwich gts = new GestorTipoSandwich();
        return gts.listaPorNombre(nombre);
    }

    public Collection listaCartaSandwich() throws errorConexionBD {
        GestorSandwich gs = new GestorSandwich();
        return gs.lista();
    }

    public Collection listaPedidosPendientes() {
        return null;
    }

    public Collection listaTipoSandwich() throws errorConexionBD {
        GestorTipoSandwich gts = new GestorTipoSandwich();
        return gts.lista();
    }

    public boolean marcaPedidoFinalizado(int idPedido) {
        return false;
    }

    public boolean modificacionSandwich(Sandwich sandwich) throws errorConexionBD {
        GestorSandwich gs = new GestorSandwich();
        gs.guarda(sandwich);
        return true;
    }

    public boolean modificacionTipoSandwich(TipoSandwich tipoSandwich) throws errorConexionBD {
        GestorTipoSandwich gts = new GestorTipoSandwich();
        gts.guarda(tipoSandwich);
        return true;
    }
}
