import eprog.EprogIO;
import eprog.EprogException;

public class Genetics {

    static String WB1, WB2, NeueWB;

    static String[] AlleMoeglichenWB = { "RR", "BB", "GG", "RG", "RB", "BG", "GR", "BR", "GB" };

    static int Zaehler, Mendel;

    static boolean MoeglicheWB1, MoeglicheWB2;

    public static void main(String[] Args) {
        Zaehler = 0;
        Mendel = 3;
        WB1 = EprogIO.readWord();
        WB2 = EprogIO.readWord();
        do {
            if (WB1.equals(AlleMoeglichenWB[Zaehler])) {
                MoeglicheWB1 = true;
            }
            if (WB2.equals(AlleMoeglichenWB[Zaehler])) {
                MoeglicheWB2 = true;
            }
            Zaehler++;
        } while (Zaehler < AlleMoeglichenWB.length);
        if (MoeglicheWB1 && MoeglicheWB2) {
            Zaehler = 0;
            do {
                if (WB1.equals(AlleMoeglichenWB[Zaehler])) {
                    Mendel--;
                }
                if (WB2.equals(AlleMoeglichenWB[Zaehler])) {
                    Mendel--;
                }
                Zaehler++;
            } while (Zaehler < 3);
            if (Mendel != 1) {
                Mendel = 2;
            }
            String NeueWB_Temp = WB1 + " " + WB2;
            char[] NeueWBArray_Temp = NeueWB_Temp.toCharArray();
            char[] NeueWBArray = { NeueWBArray_Temp[0], NeueWBArray_Temp[3], NeueWBArray_Temp[2], NeueWBArray_Temp[0], NeueWBArray_Temp[4], NeueWBArray_Temp[2], NeueWBArray_Temp[1], NeueWBArray_Temp[3], NeueWBArray_Temp[2], NeueWBArray_Temp[1], NeueWBArray_Temp[4] };
            String NeueWB = String.copyValueOf(NeueWBArray);
            EprogIO.println(NeueWB + " " + Mendel);
        } else {
            EprogIO.println("FALSCHE EINGABE");
        }
    }
}
