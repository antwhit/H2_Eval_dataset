import eprog.EprogIO;

public class money2 extends eprog.EprogIO {

    static short c_scheine[] = { 6, 800, 500, 100, 50, 10, 2 };

    static byte c_muenzen[] = { 2, 5, 1 };

    static short d_scheine[] = { 5, 400, 100, 50, 20, 4 };

    static byte d_muenzen[] = { 4, 10, 5, 2, 1 };

    public static void main(String[] args) {
        String eingabe;
        int betrag = 0;
        eingabe = readWord();
        try {
            betrag = Integer.parseInt(eingabe.substring(0, eingabe.length() - 1));
        } catch (NumberFormatException e) {
        }
        if ((betrag > 0) & (betrag <= 10000000)) {
            if (eingabe.endsWith("C")) println(berechnen(betrag, c_scheine, c_muenzen)); else if (eingabe.endsWith("D")) println(berechnen(betrag, d_scheine, d_muenzen)); else println("FALSCHE EINGABE");
        } else println("FALSCHE EINGABE");
    }

    static String berechnen(int betrag, short scheine[], byte muenzen[]) {
        int zaehler;
        int betragNeu = 0;
        boolean nurMuenzen = true;
        String ausgabe = "";
        if (betrag >= scheine[scheine[0]]) {
            nurMuenzen = false;
            ausgabe = ausgabe.concat("S");
            for (zaehler = 1; zaehler <= scheine[0]; zaehler++) {
                betragNeu = betrag / scheine[zaehler];
                if (betragNeu != 0) {
                    ausgabe = ausgabe.concat(" " + Short.toString(scheine[zaehler]));
                    ausgabe = ausgabe.concat(" " + Integer.toString(betragNeu));
                }
                betrag %= scheine[zaehler];
            }
        }
        if (betrag != 0) {
            if (nurMuenzen == false) ausgabe = ausgabe.concat(" ");
            ausgabe = ausgabe.concat("M");
            for (zaehler = 1; zaehler <= muenzen[0]; zaehler++) {
                betragNeu = betrag / muenzen[zaehler];
                if (betragNeu != 0) {
                    ausgabe = ausgabe.concat(" " + Short.toString(muenzen[zaehler]));
                    ausgabe = ausgabe.concat(" " + Integer.toString(betragNeu));
                }
                betrag %= muenzen[zaehler];
            }
        }
        return ausgabe;
    }
}
