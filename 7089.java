import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ServidorModelo {

    private ServiciosRemotosComprador scomp;

    private ServiciosRemotosAdminAux sadaux;

    private ServiciosRemotosAcceso sra;

    public ServidorModelo() throws errorConexionBD, RemoteException {
        scomp = new ServiciosCompradorImpl();
        sadaux = new ServiciosAdminAuxImpl();
        sra = new ServiciosAccesoImpl();
        createRegistry(1099);
    }

    public void encenderServidor() throws RemoteException, MalformedURLException {
        Naming.rebind("rmi://localhost/ServiciosComprador", scomp);
        Naming.rebind("rmi://localhost/ServiciosAdAux", sadaux);
        Naming.rebind("rmi://localhost/ServiciosAcceso", sra);
    }

    public void apagarServidor() throws RemoteException, MalformedURLException, NotBoundException {
        Naming.unbind("rmi://localhost/ServiciosComprador");
        Naming.unbind("rmi://localhost/ServiciosAdAux");
        Naming.unbind("rmi://localhost/ServiciosAcceso");
        scomp.desactivarRecursos();
        sadaux.desactivarRecursos();
        sra.desactivarRecursos();
    }

    private static void createRegistry(int hostport) {
        try {
            LocateRegistry.createRegistry(hostport);
            LocateRegistry.getRegistry(hostport);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Activating Java RmiRegistry.......");
            java.lang.Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.err.println("InterruptedException: " + e);
        }
    }
}
