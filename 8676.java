public class Blort {

    public static boolean staticBoolean;

    public static byte staticByte;

    public static char staticChar;

    public static short staticShort;

    public static int staticInt;

    public static long staticLong;

    public static float staticFloat;

    public static double staticDouble;

    public static Object staticObject;

    public static Object test1() {
        int x = staticByte + staticChar + staticShort + staticInt + (int) staticLong + (int) staticFloat + (int) staticDouble;
        if (staticBoolean && (x > 0)) {
            ;
            return staticObject;
        } else {
            return null;
        }
    }

    public static void test2(boolean b, int i, Object o) {
        staticBoolean = b;
        staticByte = (byte) i;
        staticChar = (char) i;
        staticShort = (short) i;
        staticInt = i;
        staticLong = i;
        staticFloat = i;
        staticDouble = i;
        staticObject = o;
    }
}
