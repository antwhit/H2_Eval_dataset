public class Blort {

    public static void caught() {
    }

    public static void zorch(int x) {
    }

    public static void test1(int x) {
        try {
            x = 0;
        } catch (RuntimeException ex) {
            caught();
        }
    }

    public static void test2(String[] sa) {
        try {
            int x = sa.length;
        } catch (RuntimeException ex) {
            caught();
        }
    }

    public static void test3() {
        try {
            zorch(1);
        } catch (RuntimeException ex) {
            caught();
        }
    }

    public static void test4(String[] sa) {
        try {
            zorch(sa.length);
        } catch (RuntimeException ex) {
            caught();
        }
    }
}
