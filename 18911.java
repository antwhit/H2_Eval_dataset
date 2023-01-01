/**
 * Classe Travaux<br/>
 * <b>Travaux est une classe fille de Extra.</b>
 * <p>
 * Travaux h�rite de la classe Extra donc de tous ses attributs et m�thodes m�me si elle 
 * ne les utilise pas tous. Travaux repr�sente un type d'extra . Un extra peut �tre un 
 * croisement, un radar ou un b�timent.
 * <br/>Un attribut suppl�mentaire � la classe m�re:
 * <ul> <li>la nouvelle limitation de vitesse, modifiable</li></ul>
 * @see Extra
 * </p>
 * 
 * @author groupe2
 * version 1.0
 */
public class Travaux extends Extra {

    /**
	 * Le limite est un octet qui d�finit la nouvelle limitation de vitesse dans la zone 
	 * de travaux.
	 */
    private byte limite;

    /**
	 * Constructeur de zone de travaux<br/>
	 * <p>
	 * Construction de zone de travaux � partir de sa nouvelle limite ainsi que, son 
	 * identifiant de zone et ses coordonn�es en latitude et longitude. Par ailleurs, � 
	 * chaque fois qu'une zone de travaux est cr��e alors il l'ajoute � la m�moire du GPS.
	 * @param vitesse, la nouvelle vitesse maximale sur cette zone
	 * @param idZone, l'identifiant de la zone
	 * @param x, la latitude
	 * @param y, la longitude
	 * 
	 * @see Extra#setId(byte)
	 * @see Extra#setLongueur(short)
	 * @see Extra#setIdZone(short)
	 * @see Extra#setTypeZone(byte)
	 * @see Extra#setX(int)
	 * @see Extra#setY(int) 
	 * @see Travaux#setLimite(byte)
	 * @see DonneesGPS#ajouterTrav(Travaux)
	 * </p>
	 */
    public Travaux(byte limite, short idZone, int x, int y) {
        this.setId((byte) 0);
        this.setLongueur((short) (12));
        this.setIdZone(idZone);
        this.setLimite(limite);
        this.setTypeZone((byte) 0);
        this.setX(x);
        this.setY(y);
        DonneesGPS.ajouterTrav(this);
    }

    /**
	 * Modificateur de la vitesse maximale dans une zone de travaux.
	 * @param limite: la nouvelle vitesse maximale dans cette zone de travaux.
	 */
    public void setLimite(byte limite) {
        this.limite = limite;
    }

    /**
	 * Accesseur de la vitesse maximale dans une zone de travaux.
	 * @return limite: la vitesse � ne pas d�passer dans cette zone.
	 */
    public byte getLimite() {
        return limite;
    }
}
