import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Collection;

public class ServiciosCocinaModelo extends ServiciosAccesoModelo {

    ServiciosRemotosCocina src;

    public ServiciosCocinaModelo() throws MalformedURLException, RemoteException, NotBoundException {
        super();
        src = (ServiciosRemotosCocina) Naming.lookup("rmi://localhost:1099/ServiciosCocina");
    }

    public boolean altaSandwich(Sandwich sandwich) throws errorConexionBD, RemoteException {
        return src.altaSandwich(sandwich);
    }

    public boolean altaTipoSandwich(TipoSandwich tipoSandwich) throws errorConexionBD, RemoteException {
        return src.altaTipoSandwich(tipoSandwich);
    }

    public Sandwich consultaSandwich(int idSandwich) throws errorConexionBD, RemoteException {
        return src.consultaSandwich(idSandwich);
    }

    public boolean eliminaSandwich(int idSandwich) throws errorConexionBD, RemoteException {
        return src.eliminaSandwich(idSandwich);
    }

    public Collection filtraTipoSandwich(String nombre) throws errorConexionBD, RemoteException {
        return src.filtraTipoSandwich(nombre);
    }

    public Collection listaCartaSandwich() throws errorConexionBD, RemoteException {
        return src.listaCartaSandwich();
    }

    public Collection listaPedidosPendientes() {
        return null;
    }

    public Collection listaTipoSandwich() throws errorConexionBD, RemoteException {
        return src.listaTipoSandwich();
    }

    public boolean marcaPedidoFinalizado(int idPedido) {
        return false;
    }

    public boolean modificacionSandwich(Sandwich sandwich) throws errorConexionBD, RemoteException {
        return src.modificacionSandwich(sandwich);
    }

    public boolean modificacionTipoSandwich(TipoSandwich tipoSandwich) throws errorConexionBD, RemoteException {
        return src.modificacionTipoSandwich(tipoSandwich);
    }
}
