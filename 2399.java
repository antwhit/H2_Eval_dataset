public class Cliente {

    private long id;

    private String CUIT;

    private String razonSocial;

    private Domicilio domicilio;

    private String fechaAlta;

    private boolean estaActivo;

    public Cliente(String cuit, Domicilio domicilio, boolean estaActivo, String fechaAlta, long id, String razonSocial) {
        super();
        CUIT = cuit;
        this.domicilio = domicilio;
        this.estaActivo = estaActivo;
        this.fechaAlta = fechaAlta;
        this.id = id;
        this.razonSocial = razonSocial;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCUIT() {
        return CUIT;
    }

    public void setCUIT(String cuit) {
        CUIT = cuit;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public boolean isEstaActivo() {
        return estaActivo;
    }

    public void setEstaActivo(boolean estaActivo) {
        this.estaActivo = estaActivo;
    }
}
