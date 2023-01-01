public class ArtikelSatz {

    private int anzahl;

    protected Artikel artikel;

    protected ArtikelSatz(int a, String n, int p, int g) {
        artikel = new Artikel(n, p, g);
        this.anzahl = a;
    }

    protected ArtikelSatz(int a, String n, int g) {
        artikel = new Artikel(n, g);
        anzahl = a;
    }

    protected void changeAnzahl(int a) {
        anzahl += a;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public int getGewicht() {
        return (getAnzahl() * artikel.getGewicht());
    }

    public String toString() {
        return artikel.toString() + " Anzahl: " + anzahl + "\n";
    }
}
