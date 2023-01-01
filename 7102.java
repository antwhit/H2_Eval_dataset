import java.util.Collection;

/** StringCollection for Java 1.3 */
public class StringCollection {

    private Collection iCollection;

    public StringCollection(Collection rCollection) {
        iCollection = rCollection;
    }

    public boolean add(String rString) {
        String value_String = rString;
        String object_String = value_String;
        return iCollection.add(object_String);
    }

    public boolean addAll(StringCollection rStringCollection) {
        return iCollection.addAll(rStringCollection.getCollection());
    }

    public void clear() {
        iCollection.clear();
    }

    public boolean contains(String rString) {
        String value_String = rString;
        String object_String = value_String;
        return iCollection.contains(object_String);
    }

    public boolean containsAll(StringCollection rStringCollection) {
        return iCollection.containsAll(rStringCollection.getCollection());
    }

    public boolean equals(Object rObject) {
        if (rObject instanceof StringCollection) {
            return iCollection.equals(((StringCollection) rObject).getCollection());
        } else {
            return false;
        }
    }

    public int hashCode() {
        return iCollection.hashCode();
    }

    public boolean isEmpty() {
        return iCollection.isEmpty();
    }

    public StringIterator iterator() {
        return new StringIterator(iCollection.iterator());
    }

    public boolean remove(String rString) {
        String value_String = rString;
        String object_String = value_String;
        return iCollection.remove(object_String);
    }

    public boolean removeAll(StringCollection rStringCollection) {
        return iCollection.removeAll(rStringCollection.getCollection());
    }

    public boolean retainAll(StringCollection rStringCollection) {
        return iCollection.retainAll(rStringCollection.getCollection());
    }

    public int size() {
        return iCollection.size();
    }

    public String[] toArray() {
        return toArray(new String[] {});
    }

    public String[] toArray(String[] rString) {
        String[] objectArray_String = (String[]) iCollection.toArray(new String[] {});
        return objectArray_String;
    }

    public Collection getCollection() {
        return iCollection;
    }
}
