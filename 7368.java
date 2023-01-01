/**
 * @author unascribed
 */
class TestClassInitializerA {

    static {
        System.out.println("clinit called for TestClassInitializerA");
    }

    static int f() {
        System.out.println("TestClassInitializerA.f called");
        return 123;
    }

    static int i = f();
}

class TestClassInitializerB {

    TestClassInitializerB() {
    }

    static {
        System.out.println("clinit called for TestClassInitializerB");
    }

    int f() {
        System.out.println("TestClassInitializerB.f called");
        return 456;
    }
}

class TestClassInitializerC {

    static {
        System.out.println("clinit called for TestClassInitializerC");
    }
}

class TestClassInitializerD extends TestClassInitializerC {

    static {
        System.out.println("clinit called for TestClassInitializerD");
    }

    static int i = 123;
}

class TestClassInitializer {

    public static void main(String args[]) {
        run();
    }

    public static void run() {
        System.out.println("TestClassInitializer");
        int i = TestClassInitializerA.i;
        System.out.println(i);
        TestClassInitializerB b = new TestClassInitializerB();
        System.out.println(b.f());
        TestClassInitializerD d = new TestClassInitializerD();
        System.out.println(d.i);
    }
}
