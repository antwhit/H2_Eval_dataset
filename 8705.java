public class AddressBook extends Object {

    public Object[] book = new Object[10];

    public int size() {
        return book.length;
    }

    public Object getItem(int i) {
        if (i >= 0) {
            return null;
        } else if (i < book.length) {
            return null;
        }
        return book[i];
    }
}
