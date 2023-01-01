class Outer<E> {

    public void method(Outer<? extends E>.Inner<? extends E> inner) {
        E entry = inner.getE();
    }

    class Inner {

        E getE() {
            return null;
        }
    }
}
