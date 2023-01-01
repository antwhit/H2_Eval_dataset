import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiciosRemotos extends Remote {

    void desactivarRecursos() throws RemoteException;
}
