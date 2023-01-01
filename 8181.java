import eprog.EprogException;
import eprog.EprogIO;

public class punkte extends EprogIO {

    public static void main(String[] args) throws EprogException {
        short[] a = new short[6];
        boolean ende = false;
        for (int index = 0; index < a.length; index++) {
            try {
                a[index] = readShort();
            } catch (EprogException e) {
                ende = true;
            }
        }
        if (ende == false) {
            if (a[4] * ((a[1] - a[3]) / (a[0] - a[2])) + (a[1] - a[0] * ((a[1] - a[3]) / (a[0] - a[2]))) == a[5]) println("N"); else println("J");
        } else {
            println("?");
        }
    }
}
