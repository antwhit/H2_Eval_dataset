class T {

    /** var.
     *  @deprecated . */
    int var;

    /** f.
     *  @deprecated . */
    void f() {
    }

    /** g.
     *  @deprecated . */
    void g() {
        f();
    }

    void h() {
        f();
    }

    /** T.
     *  @deprecated . */
    T() {
    }

    /** T.
     *  @deprecated . */
    T(int i) {
        this();
    }

    T(float f) {
        this();
    }

    void xyzzy() {
        new T();
        new T(1.4f);
    }

    /** plugh.
     *  @deprecated . */
    void plugh() {
        new T();
        new T(1.45f);
    }

    /** calcx..
     *  @deprecated . */
    int calcx() {
        return 0;
    }
}

class U extends T {

    /** f.
     * @deprecated . */
    void f() {
    }

    void g() {
        super.g();
        var = 12;
    }

    U() {
    }

    U(int i) {
        super(i);
    }

    U(float f) {
        super(1.3f);
    }
}

class V extends T {
}

/** W.
 * @deprecated . */
class W extends T {

    /** W.
     * @deprecated . */
    static {
        new T(1.3f).g();
    }

    /** W.
     * @deprecated . */
    {
        new T(1.3f).g();
    }

    {
        new T(1.3f).g();
    }

    int x = calcx();

    /** y.
     * @deprecated . */
    int y = calcx();
}

/** X.
 * @deprecated . */
class X {
}

class Y extends X {
}

/** Z.
 * @deprecated . */
class Z extends X {
}
