import java.util.*;

public class IntStorehouse {

    private LinkedList<Integer> list = new LinkedList<Integer>();

    public void push(Integer v) {
        list.addFirst(v);
    }

    public void put(Integer v) {
        list.addFirst(v);
    }

    public Integer top() {
        return list.getFirst();
    }

    public Integer pop() {
        return list.removeFirst();
    }

    public Integer get() {
        return list.removeLast();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public void clear() {
        list.clear();
    }
}
