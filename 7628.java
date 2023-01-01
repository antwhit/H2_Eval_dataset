import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ServidorModelo {

    private ServiciosRemotosEjemplo sre;

    private ServiciosRemotosComprador scomp;

    private ServiciosRemotosCocina scoc;

    private ServiciosRemotosAdminAux sadaux;

    private ServiciosRemotosAcceso sra;

    public ServidorModelo() throws errorConexionBD, RemoteException {
        sre = new ServiciosEjemploImpl();
        scomp = new ServiciosCompradorImpl();
        scoc = new ServiciosCocinaImpl();
        sadaux = new ServiciosAdminAuxImpl();
        sra = new ServiciosAccesoImpl();
    }

    public void encenderServidor() throws RemoteException, MalformedURLException {
        Naming.rebind("rmi://localhost/ServiciosEjemplo", sre);
        Naming.rebind("rmi://localhost/ServiciosComprador", scomp);
        Naming.rebind("rmi://localhost/ServiciosCocina", scoc);
        Naming.rebind("rmi://localhost/ServiciosAdAux", sadaux);
        Naming.rebind("rmi://localhost/ServiciosAcceso", sra);
    }

    public void apagarServidor() throws RemoteException, MalformedURLException, NotBoundException {
        Naming.unbind("rmi://localhost/ServiciosEjemplo");
        Naming.unbind("rmi://localhost/ServiciosComprador");
        Naming.unbind("rmi://localhost/ServiciosCocina");
        Naming.unbind("rmi://localhost/ServiciosAdAux");
        Naming.unbind("rmi://localhost/ServiciosAcceso");
        sre.desactivarRecursos();
        scomp.desactivarRecursos();
        scoc.desactivarRecursos();
        sadaux.desactivarRecursos();
        sra.desactivarRecursos();
    }
}
