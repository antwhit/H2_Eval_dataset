class TestMonitorUnwind {

    public static void main(String args[]) {
        runTest();
    }

    public static void runTest() {
        SystemOut.println("TestMonitorUnwind");
        try {
            new TestMonitorUnwind().foo(1, 0);
        } catch (Exception e) {
        }
        try {
            error();
        } catch (Exception e) {
        }
        SystemOut.println("bye\n");
    }

    synchronized int foo(int a, int b) {
        return bar(a, b);
    }

    synchronized int bar(int a, int b) {
        int c = a / b;
        return c;
    }

    static synchronized int error() throws Exception {
        throw new Exception("oops");
    }
}
