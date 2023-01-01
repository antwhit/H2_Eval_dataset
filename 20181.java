class T1420expression2 {

    void die() {
        throw new RuntimeException();
    }

    void foo() {
        System.exit(1);
        die();
        Object o = new Object[Integer.MAX_VALUE][Integer.MAX_VALUE][Integer.MAX_VALUE];
        foo();
        int i;
    }
}
