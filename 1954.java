public class GcThread extends Thread {

    public static Object lock = null;

    public static int gcCalled = 0;

    public void run() {
        while (true) {
            synchronized (GcThread.lock) {
                try {
                    GcThread.lock.wait();
                    System.gc();
                    gcCalled = 1;
                } catch (InterruptedException e) {
                    break;
                } catch (Exception e) {
                }
            }
        }
    }
}
