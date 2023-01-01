import java.util.ListIterator;

/** StringListIterator for Java 1.3 */
public class StringListIterator extends StringIterator {

    ListIterator iListIterator;

    public StringListIterator(ListIterator rListIterator) {
        super(rListIterator);
        iListIterator = rListIterator;
    }

    public void add(String rString) {
        String value_String = rString;
        String object_String = value_String;
        iListIterator.add(object_String);
    }

    public boolean hasNext() {
        return iListIterator.hasNext();
    }

    public boolean hasPrevious() {
        return iListIterator.hasPrevious();
    }

    public String next() {
        Object object = iListIterator.next();
        String object_String = (String) object;
        String value_String = object_String;
        return value_String;
    }

    public int nextIndex() {
        return iListIterator.nextIndex();
    }

    public String previous() {
        Object object = iListIterator.previous();
        String object_String = (String) object;
        String value_String = object_String;
        return value_String;
    }

    public int previousIndex() {
        return iListIterator.previousIndex();
    }

    public void remove() {
        iListIterator.remove();
    }

    public void set(String rValue) {
        String value_String = rValue;
        String object_String = value_String;
        iListIterator.set(object_String);
    }

    public ListIterator getListIterator() {
        return iListIterator;
    }
}
