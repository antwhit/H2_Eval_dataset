import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ServiciosAccesoModelo extends ServiciosModelo {

    ServiciosRemotosAcceso sra;

    boolean accesible = false;

    public ServiciosAccesoModelo() throws MalformedURLException, RemoteException, NotBoundException {
        super();
        sra = (ServiciosRemotosAcceso) Naming.lookup("rmi://localhost:1099/ServiciosAcceso");
    }

    public ServiciosModelo login(String usuario, String password, boolean isCliente) throws errorConexionBD, errorSQL, MalformedURLException, RemoteException, NotBoundException {
        System.out.println("ServiciosAccesoModelo.login()");
        ServiciosModelo s = null;
        System.out.println("Ejecutando ServiciosRemotosAcceso.login()");
        u = sra.login(usuario, password, isCliente);
        System.out.println("Acceso" + u.getNombre());
        if (u instanceof Empleado) {
            System.out.println("Usuario es empleado");
            Empleado e = (Empleado) u;
            if (e.getRol().equals("Cocina")) {
                System.out.println("Rol: Cocina");
                s = new ServiciosCocinaModelo();
            } else if (e.getRol().equals("AdAux")) {
                System.out.println("Rol: AdAux");
                s = new ServiciosAdAuxModelo();
            } else {
                System.out.println(e.getRol().toString() + ": Sin acceso !!!");
            }
        } else {
            s = new ServiciosCompradorRegistradoModelo();
            s.setU(u);
        }
        accesible = true;
        return s;
    }

    public boolean isAccesible() {
        return accesible;
    }

    public void setAcceso(boolean acceso) {
        this.accesible = acceso;
    }
}
