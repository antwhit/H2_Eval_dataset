class Rule158 extends C {

    void f(int x) {
    }

    void f(boolean b) {
    }

    void f(Object o) {
    }

    void g(Object p) {
    }
}

abstract class C {

    abstract void f(int x);

    void f(Object o) {
        int p = 4;
    }
}

class D {

    void h(int x) {
        int y = 4;
    }
}

class Z extends D {

    void h(int x) {
        int y = 5;
    }
}
