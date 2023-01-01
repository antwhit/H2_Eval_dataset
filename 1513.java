import java.rmi.RemoteException;
import java.util.Collection;

public interface ServiciosRemotosCocina extends ServiciosRemotos {

    public Collection listaCartaSandwich() throws errorConexionBD, RemoteException;

    public boolean altaSandwich(Sandwich sandwich) throws errorConexionBD, RemoteException;

    public Sandwich consultaSandwich(int idSandwich) throws errorConexionBD, RemoteException;

    public boolean modificacionSandwich(Sandwich sandwich) throws errorConexionBD, RemoteException;

    public boolean eliminaSandwich(int idSandwich) throws errorConexionBD, RemoteException;

    public Collection filtraTipoSandwich(String nombre) throws errorConexionBD, RemoteException;

    public Collection listaTipoSandwich() throws errorConexionBD, RemoteException;

    public boolean altaTipoSandwich(TipoSandwich tipoSandwich) throws errorConexionBD, RemoteException;

    public boolean modificacionTipoSandwich(TipoSandwich tipoSandwich) throws errorConexionBD, RemoteException;

    public Collection listaPedidosPendientes() throws errorConexionBD, RemoteException;

    public boolean marcaPedidoFinalizado(int idPedido) throws errorConexionBD, RemoteException;
}
