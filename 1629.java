import java.io.Serializable;
import java.util.Date;

public class Promocion implements Serializable {

    private int id;

    private int idTipoPromocion;

    private int idSandwich;

    private int idProducto;

    private double valor;

    private int puntosMinimos;

    private Date fechaBaja = null;

    private static final long serialVersionUID = 1L;

    public Promocion() {
    }

    public Promocion(int pId, int pIdTipoPromocion, int pIdSandwich, int pIdProducto, double pValor, int pPuntosMinimos) {
        id = pId;
        idTipoPromocion = pIdTipoPromocion;
        idSandwich = pIdSandwich;
        valor = pValor;
        puntosMinimos = pPuntosMinimos;
        idProducto = pIdProducto;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public int getIdSandwich() {
        return idSandwich;
    }

    public void setIdSandwich(int idSandwich) {
        this.idSandwich = idSandwich;
    }

    public int getIdTipoPromocion() {
        return idTipoPromocion;
    }

    public void setIdTipoPromocion(int idTipoPromocion) {
        this.idTipoPromocion = idTipoPromocion;
    }

    public int getPuntosMinimos() {
        return puntosMinimos;
    }

    public void setPuntosMinimos(int puntosMinimos) {
        this.puntosMinimos = puntosMinimos;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return (getId() + " " + getValor() + " " + getPuntosMinimos());
    }
}
