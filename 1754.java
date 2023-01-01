import java.util.Date;
import java.util.Iterator;

/** StringIterator for Java 1.3 */
public class StringIterator {

    Iterator iIterator;

    public StringIterator(Iterator rIterator) {
        iIterator = rIterator;
    }

    public boolean hasNext() {
        return iIterator.hasNext();
    }

    public String next() {
        Object object = iIterator.next();
        String object_String = (String) object;
        String value_String = object_String;
        return value_String;
    }

    public void remove() {
        iIterator.remove();
    }

    public Iterator getIterator() {
        return iIterator;
    }
}
