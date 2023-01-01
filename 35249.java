public class List extends Collection {

    public int size() {
        return 0;
    }

    public Object getItem(int i) {
        return null;
    }

    public Iterator iterator() {
        return new Iterator() {

            int current = 0;

            public boolean hasNext() {
                return current < size();
            }

            public Object next() {
                return getItem(current++);
            }
        };
    }
}
