import java.util.Vector;

public class StringVector extends ListVector {

    public StringVector() {
        super(10);
        index = -1;
    }

    public StringVector(int i) {
        super(i);
        index = -1;
    }

    public StringVector(String s) {
        index = -1;
        addElement(s);
    }

    public StringVector(int i, int j) {
        super(i, j);
        index = -1;
    }

    public String getSymbol(int i) {
        return (String) elementAt(i);
    }

    public void put(int i, String s) {
        if (size() < i + 1) {
            setSize(i + 10);
        }
        setElementAt(s, i);
    }

    public void add(String s) {
        addElement(s);
    }

    public String toXML() {
        if (getTag().equals("")) {
            setTag("<Strings>");
        }
        return super.toXML();
    }

    public void reset() {
        index = -1;
    }

    public boolean isNext() {
        return index + 1 < size();
    }

    public String getNextString() {
        if (isNext()) {
            return (String) elementAt(++index);
        } else {
            return null;
        }
    }

    public String getString() {
        if (index > -1 && index < size()) {
            return (String) elementAt(index);
        } else {
            return null;
        }
    }

    public synchronized Object clone(boolean flag) {
        return clone();
    }

    public void replace(String s) {
        setElementAt(s, index);
    }

    public void delete() {
        removeElementAt(index);
        index--;
    }

    public StringVector append(StringVector stringvector) {
        stringvector.reset();
        for (; stringvector.isNext(); addElement(stringvector.getNextString())) {
        }
        return this;
    }

    int index;

    public static String Eol = System.getProperty("line.separator");
}
