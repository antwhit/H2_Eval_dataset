public class Blort {

    public static Object zorch1(String s) {
        return null;
    }

    public static void test1() {
        try {
            zorch1("x");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void zorch2(String s) {
    }

    public static void test2() {
        try {
            zorch2("x");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static int zorch3(String s) {
        return 0;
    }

    public static void test3() {
        try {
            zorch3("x");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Object zorch4(int x) {
        return null;
    }

    public static void test4() {
        try {
            zorch4(1);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Object zorch5(int x) {
        return null;
    }

    public static Object test5() {
        try {
            return zorch5(1);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
