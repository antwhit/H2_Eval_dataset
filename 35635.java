import java.util.Date;
import java.util.Iterator;

/** booleanIterator for Java 1.3 */
public class booleanIterator {

    Iterator iIterator;

    public booleanIterator(Iterator rIterator) {
        iIterator = rIterator;
    }

    public boolean hasNext() {
        return iIterator.hasNext();
    }

    public boolean next() {
        Object object = iIterator.next();
        Boolean object_boolean = (Boolean) object;
        boolean value_boolean = object_boolean.booleanValue();
        return value_boolean;
    }

    public void remove() {
        iIterator.remove();
    }

    public Iterator getIterator() {
        return iIterator;
    }
}
