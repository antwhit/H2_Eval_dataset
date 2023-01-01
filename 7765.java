import java.util.ArrayList;

public class Timer implements Runnable {

    private final ArrayList<NeedsTimer> list;

    private final int timeInterval;

    public Timer(ArrayList<NeedsTimer> l, int t) {
        list = l;
        timeInterval = t;
    }

    public void start() {
        new Thread(this).start();
    }

    public void run() {
        long time = System.currentTimeMillis();
        while (true) {
            if (System.currentTimeMillis() - time > timeInterval) {
                time = System.currentTimeMillis();
                for (int i = 0; i < list.size(); list.get(i++).step()) ;
            }
        }
    }
}
