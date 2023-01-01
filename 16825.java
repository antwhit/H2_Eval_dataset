import java.util.Collection;

public interface ServiciosRemotosCocina extends ServiciosRemotos {

    public Collection listaCartaSandwich() throws errorConexionBD;

    public boolean altaSandwich(Sandwich sandwich) throws errorConexionBD;

    public Sandwich consultaSandwich(int idSandwich) throws errorConexionBD;

    public boolean modificacionSandwich(Sandwich sandwich) throws errorConexionBD;

    public boolean eliminaSandwich(int idSandwich) throws errorConexionBD;

    public Collection filtraTipoSandwich(String nombre) throws errorConexionBD;

    public Collection listaTipoSandwich() throws errorConexionBD;

    public boolean altaTipoSandwich(TipoSandwich tipoSandwich) throws errorConexionBD;

    public boolean modificacionTipoSandwich(TipoSandwich tipoSandwich) throws errorConexionBD;

    public Collection listaPedidosPendientes() throws errorConexionBD;

    public boolean marcaPedidoFinalizado(int idPedido) throws errorConexionBD;
}
