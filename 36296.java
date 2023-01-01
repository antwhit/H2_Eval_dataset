class TestInstanceOf {

    public static void main(String args[]) {
        runTest();
    }

    public static void runTest() {
        SystemOut.println("TestInstanceOf");
        Object o1 = new TestInstanceOf();
        Object o2[] = new TestInstanceOf[2];
        Object o3[][] = new Object[2][];
        o3[0] = new TestInstanceOf[4];
        o3[1] = new TestInstanceOf[4];
        int o4[] = new int[2];
        SystemOut.print("\nwant: true false false false\n got: ");
        test(o1);
        SystemOut.print("\nwant: false true false false\n got: ");
        test(o2);
        SystemOut.print("\nwant: false false false false\n got: ");
        test(o3);
        SystemOut.print("\nwant: false false false true\n got: ");
        test(o4);
        o1 = (TestInstanceOf) o1;
        SystemOut.println("\nwant: class cast exception");
        try {
            o1 = (String) o1;
        } catch (ClassCastException e) {
            SystemOut.println(" got: class cast exception");
        }
    }

    static void test(Object o) {
        boolean b1 = o instanceof TestInstanceOf;
        boolean b2 = o instanceof TestInstanceOf[];
        boolean b3 = o instanceof TestInstanceOf[][];
        boolean b4 = o instanceof int[];
        SystemOut.println(b1 + " " + b2 + " " + b3 + " " + b4);
    }
}
