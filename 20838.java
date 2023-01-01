/**
 *
 * @author Nicolas
 */
public class HorlogeLamport {

    private int compteur;

    public HorlogeLamport() {
        this.compteur = 0;
    }

    public void incrementeHorloge() {
        compteur++;
    }

    public void incrementeHorloge(int valeur) {
        compteur += valeur;
    }
}
