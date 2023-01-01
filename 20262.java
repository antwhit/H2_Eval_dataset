import java.util.List;

public class SynQueue<T> {

    private List<T> buff;

    private int max;

    public synchronized void put(T val) {
        while (buff.size() == max) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        buff.add(val);
        this.notifyAll();
    }

    public synchronized T get() {
        while (buff.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        T val = buff.remove(0);
        this.notifyAll();
        return val;
    }
}
