public class Schwein {

    private String name;

    private int gewicht;

    private static int anzahl = 0;

    public static int getAnzahl() {
        return anzahl;
    }

    static {
        anzahl = 0;
    }

    public Schwein() {
        this("nobody");
    }

    public Schwein(String name) {
        setName(name);
        setGewicht(10);
        anzahl++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGewicht() {
        return gewicht;
    }

    private void setGewicht(int gewicht) {
        this.gewicht = gewicht;
    }

    public void fressen() {
        gewicht++;
    }

    @Override
    public String toString() {
        return "Schwein [name=" + name + ", gewicht=" + gewicht + "]";
    }

    protected void finalize() throws Throwable {
        anzahl--;
    }
}
