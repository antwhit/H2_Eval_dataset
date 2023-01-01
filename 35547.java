/**
 * @author unascribed
 */
public class tak_int {

    static boolean run() {
        int i = tak_int.tak(18, 12, 6);
        System.out.println("Tak_int returned: " + i);
        return true;
    }

    static int tak(int x, int y, int z) {
        if (y >= x) {
            return z;
        } else {
            return tak(tak(x - 1, y, z), tak(y - 1, z, x), tak(z - 1, x, y));
        }
    }
}
