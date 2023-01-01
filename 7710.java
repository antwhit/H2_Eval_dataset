class TestCompare {

    public static void main(String args[]) {
        runTest();
    }

    public static void runTest() {
        SystemOut.println("TestCompare");
        zero_cmp();
        i_cmp();
        l_cmp();
        f_cmp();
        d_cmp();
        a_cmp();
        null_cmp();
        str_cmp();
    }

    static void zero_cmp() {
        int i = -1;
        SystemOut.print("\nwant: 100110\n got: ");
        if (i != 0) SystemOut.print(1); else SystemOut.print(0);
        if (i == 0) SystemOut.print(1); else SystemOut.print(0);
        if (i >= 0) SystemOut.print(1); else SystemOut.print(0);
        if (i < 0) SystemOut.print(1); else SystemOut.print(0);
        if (i <= 0) SystemOut.print(1); else SystemOut.print(0);
        if (i > 0) SystemOut.print(1); else SystemOut.print(0);
        SystemOut.println();
    }

    static void i_cmp() {
        int i = -1;
        int j = 0;
        SystemOut.print("\nwant: 100110\n got: ");
        if (i != j) SystemOut.print(1); else SystemOut.print(0);
        if (i == j) SystemOut.print(1); else SystemOut.print(0);
        if (i >= j) SystemOut.print(1); else SystemOut.print(0);
        if (i < j) SystemOut.print(1); else SystemOut.print(0);
        if (i <= j) SystemOut.print(1); else SystemOut.print(0);
        if (i > j) SystemOut.print(1); else SystemOut.print(0);
        SystemOut.println();
    }

    static void l_cmp() {
        long a = 1;
        long b = 2;
        SystemOut.print("\nwant: 100010001\n got: ");
        if (a < b) SystemOut.print(1); else SystemOut.print(0);
        if (a == b) SystemOut.print(1); else SystemOut.print(0);
        if (a > b) SystemOut.print(1); else SystemOut.print(0);
        if (a < a) SystemOut.print(1); else SystemOut.print(0);
        if (a == a) SystemOut.print(1); else SystemOut.print(0);
        if (a > a) SystemOut.print(1); else SystemOut.print(0);
        if (b < a) SystemOut.print(1); else SystemOut.print(0);
        if (b == a) SystemOut.print(1); else SystemOut.print(0);
        if (b > a) SystemOut.print(1); else SystemOut.print(0);
        SystemOut.println();
    }

    static void f_cmp() {
        float a = 1;
        float b = 2;
        SystemOut.print("\nwant: 100010001\n got: ");
        if (a < b) SystemOut.print(1); else SystemOut.print(0);
        if (a == b) SystemOut.print(1); else SystemOut.print(0);
        if (a > b) SystemOut.print(1); else SystemOut.print(0);
        if (a < a) SystemOut.print(1); else SystemOut.print(0);
        if (a == a) SystemOut.print(1); else SystemOut.print(0);
        if (a > a) SystemOut.print(1); else SystemOut.print(0);
        if (b < a) SystemOut.print(1); else SystemOut.print(0);
        if (b == a) SystemOut.print(1); else SystemOut.print(0);
        if (b > a) SystemOut.print(1); else SystemOut.print(0);
        SystemOut.println();
    }

    static void d_cmp() {
        double a = 1;
        double b = 2;
        SystemOut.print("\nwant: 100010001\n got: ");
        if (a < b) SystemOut.print(1); else SystemOut.print(0);
        if (a == b) SystemOut.print(1); else SystemOut.print(0);
        if (a > b) SystemOut.print(1); else SystemOut.print(0);
        if (a < a) SystemOut.print(1); else SystemOut.print(0);
        if (a == a) SystemOut.print(1); else SystemOut.print(0);
        if (a > a) SystemOut.print(1); else SystemOut.print(0);
        if (b < a) SystemOut.print(1); else SystemOut.print(0);
        if (b == a) SystemOut.print(1); else SystemOut.print(0);
        if (b > a) SystemOut.print(1); else SystemOut.print(0);
        SystemOut.println();
    }

    static void a_cmp() {
        Object a = null;
        Object b = null;
        SystemOut.print("\nwant: 10\n got: ");
        if (a == b) SystemOut.print(1); else SystemOut.print(0);
        if (a != b) SystemOut.print(1); else SystemOut.print(0);
        SystemOut.println();
    }

    static void null_cmp() {
        Object o = null;
        SystemOut.print("\nwant: 10\n got: ");
        if (o == null) SystemOut.print(1); else SystemOut.print(0);
        if (o != null) SystemOut.print(1); else SystemOut.print(0);
        SystemOut.println();
    }

    static void str_cmp() {
        String s1 = "abc";
        String s2 = "abc";
        String s3 = "ab";
        s3 = s3 + "c";
        SystemOut.println("\nwant: true\n got: " + (s1 == s2));
        SystemOut.println("\nwant: false\n got: " + (s1 == s3));
    }
}
