import eprog.*;
import Kapaz.*;

public class Main extends EprogIO {

    public static void main(String[] args) throws EprogException {
        String INPUT = readWord();
        Check.CheckStr(INPUT);
        EprogIO.println(Math.round(Calculate.CalcStr(INPUT)));
    }
}
