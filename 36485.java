import java.io.Serializable;
import java.util.Date;

public abstract class Persona implements Serializable {

    private int id;

    private String nif;

    private String nombre;

    private String apellido1;

    private String apellido2;

    private String direccion;

    private String poblacion;

    private String telefono;

    private String movil;

    private String email;

    private Date fechaBaja;

    public Persona() {
    }

    public Persona(int pId, String pNif, String pNombre, String pApellido1, String pApellido2, String pDireccion, String pPoblacion, String pTelefono, String pMovil, String pEmail) {
        id = pId;
        nif = pNif;
        nombre = pNombre;
        apellido1 = pApellido1;
        apellido2 = pApellido2;
        direccion = pDireccion;
        poblacion = pPoblacion;
        telefono = pTelefono;
        movil = pMovil;
        email = pEmail;
        fechaBaja = null;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }
}
