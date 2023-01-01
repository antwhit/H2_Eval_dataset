import java.io.Serializable;

public class Cliente extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tarjetaCredito;

    private int puntos;

    public Cliente() {
        super();
    }

    public Cliente(int pId, String pNif, String pNombre, String pApellido1, String pApellido2, String pDireccion, String pPoblacion, String pTelefono, String pMovil, String pEmail, String pCodUsuario, String pPassword, boolean pDesactivado, String pTarjetaCredito, int pPuntos) {
        super(pId, pNif, pNombre, pApellido1, pApellido2, pDireccion, pPoblacion, pTelefono, pMovil, pEmail, pCodUsuario, pPassword, pDesactivado);
        tarjetaCredito = pTarjetaCredito;
        puntos = pPuntos;
    }

    public String getTarjetaCredito() {
        return tarjetaCredito;
    }

    public void setTarjetaCredito(String tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
