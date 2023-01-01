import java.io.Serializable;

public abstract class Usuario extends Persona implements Serializable {

    private String codUsuario = "";

    private String password = "";

    private boolean desactivado = false;

    public Usuario() {
        super();
    }

    public Usuario(int pId, String pNif, String pNombre, String pApellido1, String pApellido2, String pDireccion, String pPoblacion, String pTelefono, String pMovil, String pEmail, String pCodUsuario, String pPassword, boolean pDesactivado) {
        super(pId, pNif, pNombre, pApellido1, pApellido2, pDireccion, pPoblacion, pTelefono, pMovil, pEmail);
        codUsuario = pCodUsuario;
        password = pPassword;
        desactivado = pDesactivado;
    }

    public String getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(String codUsuario) {
        this.codUsuario = codUsuario;
    }

    public boolean isDesactivado() {
        return desactivado;
    }

    public void setDesactivado(boolean desactivado) {
        this.desactivado = desactivado;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return (getNombre() + " " + getNif() + " " + getDireccion());
    }
}
