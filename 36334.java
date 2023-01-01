final class InnerInFinalClass {

    void f() {
        new Object() {

            public final void g() {
            }
        };
    }
}
