import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class ServiciosEjemploImpl extends UnicastRemoteObject implements ServiciosRemotosEjemplo {

    GestorEjemplo ge;

    public ServiciosEjemploImpl() throws errorConexionBD, RemoteException {
        ge = new GestorEjemplo();
    }

    public Vector<Ejemplo> listaEjemplos() throws errorSQL, errorConexionBD, RemoteException {
        return ge.listaEjemplos();
    }

    public String insertarEjemplo(Ejemplo ej) throws errorSQL, errorConexionBD, RemoteException {
        return ge.insertarEjemplo(ej);
    }

    public void desactivarRecursos() throws RemoteException {
        ge.liberarRecursos();
    }
}
