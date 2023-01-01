/**
 * Classe IdConst: dérive de la classe Ident pour créer des constantes
 * 
 * @author BlobbyCompiloTeam
 *
 */
public class IdConst extends Ident implements GramConstants {

    private int valeur;

    /**
	 * Constructeur
	 * @param _type type de constantes, entier ou booleen
	 * @param _valeur valeur de cette constante
	 */
    public IdConst(int _type, int _valeur) {
        super(_type, natConst);
        valeur = _valeur;
    }

    /**
	 * Accesseur de la valeur d'une constante
	 * @return int valeur de cette constante
	 */
    public int getValeur() {
        return valeur;
    }

    /**
	 * Retourne l'affichage
	 * String
	 */
    public String toString() {
        return "(" + type + "," + nature + "," + valeur + ")";
    }
}
