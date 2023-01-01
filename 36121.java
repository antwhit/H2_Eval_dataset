import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServiciosCompradorImpl extends UnicastRemoteObject implements ServiciosRemotosComprador {

    public ServiciosCompradorImpl() throws RemoteException {
    }

    public void desactivarRecursos() {
    }
}
