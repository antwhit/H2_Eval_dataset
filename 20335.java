import java.io.Serializable;

/**
 * Classe Extra<br/>
 * <b>Extra repr�sente une zone de travaux, un croisement, un b�timent ou un radar.</b>
 * <p>
 * Extra est une classe abstraite qui impl�mente l'interface {@link Serializable}. Elle est 
 * la classe m�re de {@link Travaux}, {@link Croisement}, {@link Batiment} et {@link Radar}.
 * Elle regroupe tous les caract�res commun des extras, soit un identifiant, une localisation,
 * un type de zone, un identifiant de zone et une longueur.<br/>
 * Elle impl�mente la classe Serializable dans ses classes filles.
 * 
 * @see Serializable
 * @see Travaux
 * @see Croisement
 * @see Batiment
 * @see Radar
 * </p>
 * 
 * @author groupe2
 * version 1.0
 */
public abstract class Extra implements Serializable {

    /**
	 * id est un octet repr�sentant l'identifiant d'un extra.<br/>
	 */
    private byte id;

    /**
	 * X est un entier qui pr�cise la latitude d'un point.
	 */
    private int X;

    /**
	 * Y est un entier qui pr�cise la longitude d'un point.
	 */
    private int Y;

    /**
	 * typeZone est un octet qui code le type d'une zone.
	 */
    private byte typeZone;

    /**
	 * idZone est un nombre court qui identifie une zone.
	 */
    private short idZone;

    /**
	 * longueur est un nombre court qui donne la longueur d'un extra.
	 */
    private short longueur;

    /**
	 * Modificateur de l'identifiant d'un extra.
	 * @param id: le nouvel identifiant d'un extra.
	 */
    public void setId(byte id) {
        this.id = id;
    }

    /**
	 * Acesseur de l'identifiant d'un extra.
	 * @return id: l'identifiant d'un extra.
	 */
    public byte getId() {
        return id;
    }

    /**
	 * Accesseur de la latitude d'un extra.
	 * @return x: la latitude d'un extra.
	 */
    public int getX() {
        return X;
    }

    /**
	 * Modificateur de la latitude d'un extra.
	 * @param x: la nouvelle latitude d'un extra.
	 */
    public void setX(int x) {
        X = x;
    }

    /**
	 * Accesseur de la longitude d'un extra.
	 * @return y: la longitude d'un extra.
	 */
    public int getY() {
        return Y;
    }

    /**
	 * Modificateur de la longitude d'un extra.
	 * @param y: la nouvelle longitude d'un extra.
	 */
    public void setY(int y) {
        Y = y;
    }

    /**
	 * Acceseur du type de la zone.
	 * @return typeZone: le type de la zone d'un extra.
	 */
    public byte getTypeZone() {
        return typeZone;
    }

    /**
	* Modificateur du type de la zone.
 	* @param typeZone: le nouveau type de la zone.
 	*/
    public void setTypeZone(byte typeZone) {
        this.typeZone = typeZone;
    }

    /**
	 * Accesseur de l'identifiant de la zone de l'extra.
	 * @return idZone: l'identifiant de la zone de l'extra.
	 */
    public short getIdZone() {
        return idZone;
    }

    /**
	 * Modificateur de l'identifiant de la zone  de l'extra.
 	* @param idZone: le nouvel identifiant de la zone de l'extra.
 	*/
    public void setIdZone(short idZone) {
        this.idZone = idZone;
    }

    /**
	 * Acceseur de la longueur d'un extra.
	 * @return longueur: la longueur d'un extra.
	 */
    public short getLongueur() {
        return longueur;
    }

    /**
	 * Modificateur de la longueur d'un extra.
	 * @param longueur: la nouvelle longueur d'un extra.
 	*/
    public void setLongueur(short longueur) {
        this.longueur = longueur;
    }
}
