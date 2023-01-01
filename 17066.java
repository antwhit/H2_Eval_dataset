public class Blort {

    public static Object test1(boolean b) {
        return (b ? new String[1] : new Integer[1])[0];
    }

    public static int test2(boolean b) {
        Object o = b ? (Object) new int[1] : new float[1];
        return o.hashCode();
    }

    public static int test3(boolean b) {
        Object o = b ? (Object) new char[1] : new double[1];
        return o.hashCode();
    }

    public static int test4(boolean b) {
        Object o = b ? (Object) new long[1] : new boolean[1];
        return o.hashCode();
    }

    public static int test5(boolean b) {
        Object o = b ? (Object) new short[1] : new Object[1];
        return o.hashCode();
    }

    public static int test6(boolean b) {
        Object o = b ? (Object) new byte[1] : new boolean[1];
        return o.hashCode();
    }

    public static Object test7(boolean b) {
        return (b ? new String[1] : new int[1][])[0];
    }

    public static Object[] test8(boolean b) {
        return (b ? new String[1][] : new int[1][][])[0];
    }
}
