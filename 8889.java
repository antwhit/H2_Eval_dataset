import eprog.*;
import java.lang.*;

public class Wochentag extends EprogIO {

    public static String berechneTag(int Tag, int Monat, int Jahr) {
        if ((Tag > 31) || (Monat > 12)) return "FALSCHE EINGABE";
        int TaMo[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        boolean mod4, mod100, mod400;
        if (Jahr % 4 != 0) mod4 = false; else mod4 = true;
        if (Jahr % 100 != 0) mod100 = false; else mod100 = true;
        if (Jahr % 400 != 0) mod400 = false; else mod400 = true;
        if (mod4 == true) {
            TaMo[1] = 29;
        }
        if (mod100 == true && mod400 == false) {
            TaMo[1] = 28;
        }
        if (Tag == 0 || Monat == 0) {
            return "FALSCHE EINGABE";
        }
        if (Tag > TaMo[Monat - 1]) {
            return "FALSCHE EINGABE";
        }
        int X = (Jahr + (int) Math.floor((Jahr - 1) / 4) - (int) Math.floor((Jahr - 1) / 100) + (int) Math.floor((Jahr - 1) / 400)) % 7;
        for (int i = 0; i < (Monat - 1); ++i) Tag = Tag + TaMo[i];
        X = (X + Tag) % 7;
        String Wochentag[] = { "13467 123456", "12356 123456", "123456 56", "12356 56", "123456 123456", "1567 123567", "13467 123567" };
        return Wochentag[X];
    }
}
