interface InterfaceFoo {

    int one = 1;

    int foo();
}

interface InterfaceBar {

    int two = 2;

    int bar();
}

interface InterfaceBaz extends InterfaceFoo {

    int baz();
}

class TestInterfaceA implements InterfaceFoo, InterfaceBar {

    public int foo() {
        return 1;
    }

    public int bar() {
        return 2;
    }
}

class TestInterfaceB implements InterfaceBar, InterfaceFoo {

    public int bar() {
        return 3;
    }

    public int foo() {
        return 4;
    }
}

class TestInterfaceC extends TestInterfaceB implements InterfaceFoo {
}

class TestInterfaceD implements InterfaceBaz {

    public int foo() {
        return 5;
    }

    public int baz() {
        return 6;
    }
}

class TestInterfaceE extends TestInterfaceD {
}

class TestInterfaceCall {

    public static void main(String args[]) {
        runTest();
    }

    public static void runTest() {
        SystemOut.println("TestInterfaceCall");
        InterfaceFoo foo = null;
        foo = new TestInterfaceA();
        SystemOut.println(foo.foo());
        foo = new TestInterfaceB();
        SystemOut.println(foo.foo());
        InterfaceBar bar = null;
        bar = new TestInterfaceA();
        SystemOut.println(bar.bar());
        bar = new TestInterfaceB();
        SystemOut.println(bar.bar());
        foo = new TestInterfaceC();
        SystemOut.println(foo.foo());
        SystemOut.println(new TestInterfaceD() instanceof InterfaceBaz);
        SystemOut.println(new TestInterfaceD() instanceof InterfaceFoo);
        SystemOut.println(new TestInterfaceD() instanceof InterfaceBar);
        SystemOut.println(new TestInterfaceE() instanceof InterfaceBaz);
        SystemOut.println(new TestInterfaceE() instanceof InterfaceFoo);
        SystemOut.println(new TestInterfaceE() instanceof InterfaceBar);
    }
}
