class T4743490 {

    static class A {

        public void m(Object o, String s) {
        }
    }

    interface B {

        void m(String s, Object o);
    }

    abstract static class C extends A implements B {
    }

    abstract static class D extends C {

        void foo() {
            C c = null;
            super.m("", "");
        }
    }
}
