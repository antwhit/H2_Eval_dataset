import java.sql.SQLException;

/**
 * @author maestro
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface KarteikartenDatenErzeuger {

    /**
	 * 
	 * @param i Karteikarte, aus der die Daten entnommen wurden
	 */
    public void insertKarteikarte(Karteikarte i) throws SQLException, KigaException;
}
