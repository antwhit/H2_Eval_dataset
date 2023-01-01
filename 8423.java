public interface Aplicacion {

    void mostrarError(String mensaje, String titulo);

    void mostrarInformacion(String mensaje, String titulo);

    int confirmacio(String mensaje, String titulo);
}
