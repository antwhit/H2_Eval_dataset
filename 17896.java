import java.util.Iterator;

public class IteratorAdapter<A> implements Iterator<A> {

    private FuncIterator<A> fIter;

    private IteratorAdapter(FuncIterator<A> f) {
        this.fIter = f;
    }

    public static <A> IteratorAdapter<A> create(FuncIterator<A> f) {
        return new IteratorAdapter<A>(f);
    }

    public boolean hasNext() {
        return fIter.hasElement();
    }

    public A next() {
        if (!(fIter.hasElement())) throw new java.util.NoSuchElementException("IteratorAdapter.next()");
        A current = fIter.current();
        fIter = fIter.advance();
        return current;
    }

    public void remove() {
        throw new UnsupportedOperationException("IteratorAdapter.remove()");
    }
}
