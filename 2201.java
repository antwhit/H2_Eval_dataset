import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServicioCompradorImpl extends UnicastRemoteObject implements ServicioRemotoComprador {

    public ServicioCompradorImpl() throws RemoteException {
    }

    public void desactivarRecursos() {
    }
}
