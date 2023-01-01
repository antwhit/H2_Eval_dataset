import java.util.*;
import java.io.*;

/**
 *
 * Sortiert "tote" Spieler nach Leistugen
 * die sie erbracht haben als sie noch gelebt
 * haben
 * nimmt neue Tote auf
 * und kann sagen welcher der beste war
 * @author Eugene
 */
public class Highscore {

    static Set<Spieler> spielerCemetery;

    /**
     * Wenn man dem Spieler nach dem Spiel die
     * Highscore zeigen möchte benutzte diese Methode..
     *
     * Bevor ein Spieler in die Liste darf
     * muss sein Spielfeld auf null gesetzt werden
     * um Platz zu sparen
     *
     * belebt liste, fügt neuen spieler ein, schläfert wieder ein.
     * @param spieler
     * @return  gibt die aktuellste liste zurück
     */
    public static Set<Spieler> einfuegenSpielerInListe(Spieler spieler) throws FileNotFoundException {
        try {
            spielerCemetery = ladeListe();
            spielerCemetery.add(spieler);
            speicherAktuelleListe();
            return spielerCemetery;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
    }

    /**
     * Wenn man vor dem Spiel in die Highcore schauen
     * möchte benutze diese Methode
     * @return
     */
    public static Set<Spieler> ladeListe() throws FileNotFoundException {
        TreeSet<Spieler> belebteListe = null;
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("spielerCemetry.ser"));
            belebteListe = (TreeSet<Spieler>) input.readObject();
            input.close();
            return belebteListe;
        } catch (Exception e) {
            throw new FileNotFoundException();
        }
    }

    /**
     * Speichert die um den Spieler aktuellisierte Liste
     *
     */
    private static void speicherAktuelleListe() {
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("spielerCemetry.ser", false));
            output.writeObject(spielerCemetery);
            output.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void speichernSpielerFirstTime(Spieler s) {
        spielerCemetery = new TreeSet<Spieler>();
        spielerCemetery.add(s);
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("spielerCemetry.ser", false));
            output.writeObject(spielerCemetery);
            output.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
