import java.util.List;
import java.util.ListIterator;

/** StringList for Java 1.3 */
public class StringList extends StringCollection {

    private List iList;

    public StringList(List rList) {
        super(rList);
        iList = rList;
    }

    public void add(int rIndex, String rString) {
        String value_String = rString;
        String object_String = value_String;
        iList.add(rIndex, object_String);
    }

    public boolean add(String rString) {
        String value_String = rString;
        String object_String = value_String;
        return iList.add(object_String);
    }

    public boolean addAll(StringCollection rStringCollection) {
        return iList.addAll(rStringCollection.getCollection());
    }

    public void clear() {
        iList.clear();
    }

    public boolean contains(String rString) {
        String value_String = rString;
        String object_String = value_String;
        return iList.contains(object_String);
    }

    public boolean containsAll(StringCollection rStringCollection) {
        return iList.containsAll(rStringCollection.getCollection());
    }

    public boolean equals(Object rObject) {
        if (rObject instanceof StringList) {
            return iList.equals(((StringList) rObject).getList());
        } else {
            return false;
        }
    }

    public int hashCode() {
        return iList.hashCode();
    }

    public int indexOf(String rString) {
        String value_String = rString;
        String object_String = value_String;
        return iList.indexOf(object_String);
    }

    public boolean isEmpty() {
        return iList.isEmpty();
    }

    public StringIterator iterator() {
        return new StringIterator(iList.iterator());
    }

    public int lastIndexOf(String rString) {
        String value_String = rString;
        String object_String = value_String;
        return iList.lastIndexOf(object_String);
    }

    public StringListIterator listIterator() {
        return new StringListIterator(iList.listIterator());
    }

    public StringListIterator listIterator(int rIndex) {
        return new StringListIterator(iList.listIterator(rIndex));
    }

    public String remove(int rIndex) {
        Object object = iList.remove(rIndex);
        String object_String = (String) object;
        String value_String = object_String;
        return value_String;
    }

    public boolean remove(String rString) {
        String value_String = rString;
        String object_String = value_String;
        return iList.remove(object_String);
    }

    public boolean removeAll(StringCollection rStringCollection) {
        return iList.removeAll(rStringCollection.getCollection());
    }

    public boolean retainAll(StringCollection rStringCollection) {
        return iList.retainAll(rStringCollection.getCollection());
    }

    public int size() {
        return iList.size();
    }

    public StringList subList(int rFromIndex, int rToIndex) {
        return new StringList(iList.subList(rFromIndex, rToIndex));
    }

    public String[] toArray() {
        return toArray(new String[] {});
    }

    public String[] toArray(String[] rString) {
        String[] objectArray_String = (String[]) iList.toArray(new String[] {});
        return objectArray_String;
    }

    public List getList() {
        return iList;
    }
}
