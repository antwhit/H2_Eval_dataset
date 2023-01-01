public class tak_long {

    static boolean run() {
        long i = tak_long.tak(18L, 12L, 6L);
        System.out.println("Tak_long returned: " + i);
        return true;
    }

    static long tak(long x, long y, long z) {
        if (y >= x) {
            return z;
        } else {
            return tak(tak(x - 1, y, z), tak(y - 1, z, x), tak(z - 1, x, y));
        }
    }
}
