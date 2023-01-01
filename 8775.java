/**
 * A multithreaded program exhibiting a problem with synchronization: One thread is dependent on the completion
 * of another thread's work, but this is not expressed in code.
 *
 * @author Mathias Ricken
 */
public class SyncProblem1 {

    public static void main(String[] args) {
        (new SyncProblem1()).run();
    }

    private Character _lock = new Character('1');

    private volatile int _sharedInt = 0;

    public void run() {
        Thread worker = new Thread(new Runnable() {

            public void run() {
                System.out.println("Worker thread is running");
                lengthyProcessing("Worker thread", 50, '+');
                synchronized (_lock) {
                    ++_sharedInt;
                    System.out.println("_sharedInt == 1 == " + _sharedInt);
                }
            }
        });
        worker.start();
        lengthyProcessing("Main thread", 100000, '.');
        synchronized (_lock) {
            --_sharedInt;
            System.out.println("_sharedInt == 0 == " + _sharedInt);
            assert (_sharedInt == 0);
        }
    }

    private void lengthyProcessing(String threadName, int iterations, char progressChar) {
        System.out.print(threadName + " is doing work");
        for (int i = 0; i < iterations; ++i) {
            System.out.print(progressChar);
            System.out.flush();
        }
        System.out.println();
    }
}
