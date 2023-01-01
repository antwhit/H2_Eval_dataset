import java.util.*;

class gjp_listIterator {

    int index = 0;

    Vector v;

    gjp_listIterator(Vector inputVector) {
        v = inputVector;
    }

    public static void main(String args[]) {
        Vector v = new Vector();
        gjp_listIterator gli = new gjp_listIterator(v);
        gli.add("one");
        gli.add("two");
        gli.add("three");
        System.out.println(v.size());
        while (gli.hasNext()) {
            System.out.println(gli.next());
        }
        System.out.println(v);
    }

    public void add(Object o) {
        if (index == v.size()) {
            v.add(o);
        } else {
            v.add(index, o);
        }
    }

    public boolean hasNext() {
        boolean rtn = false;
        if (index < v.size()) {
            rtn = true;
        }
        return rtn;
    }

    public int nextIndex() {
        return index + 1;
    }

    public Object next() {
        Object o = new Object();
        if (index < v.size()) {
            o = v.remove(index);
        }
        return o;
    }

    public void addAll(Collection coll) {
        if (index == v.size()) {
            v.addAll(coll);
        } else {
            v.add(index, coll);
        }
    }
}
