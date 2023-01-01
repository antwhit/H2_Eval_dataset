class InheritedPrivateImpl {

    interface I {

        public void foo();
    }

    static class C1 {

        private void foo() {
        }
    }

    abstract static class C2 extends C1 implements I {
    }
}
