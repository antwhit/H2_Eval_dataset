class TestReturn {

    public static void main(String args[]) {
        runTest();
    }

    public static void runTest() {
        SystemOut.println("TestReturn");
        void_f();
        SystemOut.print("\nwant: true\n got: ");
        SystemOut.println(boolean_f());
        SystemOut.print("\nwant: 2\n got: ");
        SystemOut.println(byte_f());
        SystemOut.print("\nwant: A\n got: ");
        SystemOut.println(char_f());
        SystemOut.print("\nwant: 4\n got: ");
        SystemOut.println(short_f());
        SystemOut.print("\nwant: 5\n got: ");
        SystemOut.println(int_f());
        SystemOut.print("\nwant: 6\n got: ");
        SystemOut.println(long_f());
        SystemOut.print("\nwant: 7.0\n got: ");
        SystemOut.println(float_f());
        SystemOut.print("\nwant: 8.0\n got: ");
        SystemOut.println(double_f());
        SystemOut.print("\nwant: null\n got: ");
        SystemOut.println(object_f());
        SystemOut.print("\nwant: null\n got: ");
        SystemOut.println(primitive_array_f());
        SystemOut.print("\nwant: null\n got: ");
        SystemOut.println(object_array_f());
    }

    static void void_f() {
        return;
    }

    static boolean boolean_f() {
        boolean x = true;
        return x;
    }

    static byte byte_f() {
        byte x = 2;
        return x;
    }

    static char char_f() {
        char x = 0x41;
        return x;
    }

    static short short_f() {
        short x = 4;
        return x;
    }

    static int int_f() {
        int x = 5;
        return x;
    }

    static long long_f() {
        long x = 6;
        return x;
    }

    static float float_f() {
        float x = 7;
        return x;
    }

    static double double_f() {
        double x = 8;
        return x;
    }

    static Object object_f() {
        Object x = null;
        return x;
    }

    static int[] primitive_array_f() {
        int x[] = null;
        return x;
    }

    static Object[] object_array_f() {
        Object x[] = null;
        return x;
    }
}
