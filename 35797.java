import java.util.Iterator;

class T6400189c<T> {

    interface A<X> extends Iterable<X> {

        Iterator<X> iterator();
    }

    interface A2<Y> extends A<Y> {

        Iterator<Y> iterator();
    }

    abstract static class B<Z> implements A<Z> {

        public abstract Iterator<Z> iterator();
    }

    abstract static class C<W> extends B<W> implements A2<W> {

        Iterator<W> test() {
            return iterator();
        }
    }
}
