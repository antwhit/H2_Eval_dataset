public class Wuerfel {

    /**
    * Attribut der Klasse Wuerfel:
    */
    private int punkte;

    /**
     * Konstruktor f�r Objekte der Klasse Wuerfel:
     */
    public Wuerfel() {
        punkte = 0;
    }

    /**
    * Methoden der Klasse Wuerfel:
    * 
    * Diese Methode gibt die gew�rfelte Augenzahl zur�ck.
    */
    public int punktzahlAngeben() {
        return this.punkte;
    }

    /**
    * 
    */
    private int zufallsZahlAusgeben(int pMaximum) {
        int wert = 0;
        wert = (int) Math.round(Math.random() * (pMaximum - 1)) + 1;
        return wert;
    }

    /**
    * 
    */
    public void rollen() {
        this.punkte = zufallsZahlAusgeben(6);
    }
}
