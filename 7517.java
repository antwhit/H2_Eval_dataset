class T4717181b {

    static class E1 extends Exception {
    }

    static class E2 extends Exception {
    }

    static class E3 extends Exception {
    }

    abstract static class Ta {

        public abstract void m() throws E1, E2;
    }

    interface Tb {

        void m() throws E2, E3;
    }

    abstract static class Tc extends Ta implements Tb {

        {
            try {
                m();
            } catch (E2 e2) {
            }
        }
    }
}
