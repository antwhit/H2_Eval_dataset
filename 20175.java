import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ControladorPantallaBuscarFactura implements ActionListener {

    private ServiciosAdAuxModelo sm = null;

    private PantallaBuscarFactura pbf = null;

    private PantallaConsultaFactura pcf = null;

    private AplicacionEmpleado ae = null;

    private int id;

    public ControladorPantallaBuscarFactura(PantallaBuscarFactura pantallaBuscarFactura, PantallaConsultaFactura pantallaConsultaFactura, AplicacionEmpleado ae) {
        this.ae = ae;
        this.pbf = pantallaBuscarFactura;
        this.pcf = pantallaConsultaFactura;
    }

    public void actionPerformed(ActionEvent arg0) {
        id = new Integer(pbf.getJTextField().getText());
        sm = (ServiciosAdAuxModelo) ae.getSm();
        try {
            sm.existeFactura(id);
            sm.consultaFactura(id);
        } catch (MalformedURLException e) {
            ae.mostrarError(e.getMessage(), "Posar titol");
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (errorConexionBD e) {
            e.printStackTrace();
        } catch (errorSQL e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    public int idf() {
        return id;
    }
}
