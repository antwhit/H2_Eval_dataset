public class Power {

    public static int powerRecursive(int n, int exp) {
        if (exp == 0) return 1; else return n * powerRecursive(n, exp - 1);
    }
}
