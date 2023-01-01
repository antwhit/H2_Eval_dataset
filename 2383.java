public class Hailstone2 {

    public static int h(int n) {
        if (n % 2 != 0) {
            return (3 * n + 1) / 2;
        } else {
            return n / 2;
        }
    }
}
