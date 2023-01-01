import java.rmi.RemoteException;
import java.util.Vector;

public interface ServiciosRemotosComprador extends ServiciosRemotos {

    public Usuario getUsuario(int id) throws errorConexionBD, errorSQL, RemoteException;

    public void setUsuario(Usuario u) throws RemoteException, errorConexionBD, errorSQL;

    public Vector<Producto> listaProductosPorFamilia(int familias) throws RemoteException, errorConexionBD, errorSQL;
}
