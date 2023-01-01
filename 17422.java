public class T6650759d {

    abstract static class A<X> {

        static <T> A<T> m(Iterable<? extends T> elements) {
            return null;
        }
    }

    abstract static class B {
    }

    abstract static class C<X extends B> {
    }

    <U extends C<V>, V extends B> Iterable<V> get(U u) {
        return null;
    }

    <U extends C<V>, V extends B> void m(U u) {
        A<V> a = A.m(get(u));
    }
}
