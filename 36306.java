import java.io.Serializable;
import java.util.Date;

public class Facturacion implements Serializable {

    int id;

    int idPedido;

    Date fecha;

    int importe;

    public Facturacion(int id, int idPedido, Date fecha, int importe) {
        super();
        this.id = id;
        this.idPedido = idPedido;
        this.fecha = fecha;
        this.importe = importe;
    }

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
}
