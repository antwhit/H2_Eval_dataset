public class Blort {

    public static Object test1() {
        return ((Object[]) null)[0];
    }

    public static void test2() {
        ((Object[]) null)[0] = null;
    }

    public static int test3() {
        return ((Object[]) null).length;
    }

    public static Object test4() {
        Object[] arr = null;
        return arr[0];
    }

    public static void test5() {
        Object[] arr = null;
        arr[0] = null;
    }

    public static int test6() {
        Object[] arr = null;
        return arr.length;
    }

    public static Object test7(Object[] arr) {
        if (check()) {
            arr = null;
        }
        return arr[0];
    }

    public static void test8(Object[] arr) {
        if (check()) {
            arr = null;
        }
        arr[0] = null;
    }

    public static int test9(Object[] arr) {
        if (check()) {
            arr = null;
        }
        return arr.length;
    }

    public static boolean check() {
        return true;
    }
}
