import lejos.nxt.LCD;
import lejos.nxt.comm.RConsole;
import lejos.realtime.PeriodicParameters;
import lejos.realtime.PriorityParameters;
import lejos.realtime.RealtimeThread;
import lejos.realtime.RelativeTime;
import lejos.util.TimeReference;

/**
 *
 * @author clement
 */
public class TestTransitivite {

    private static final Object ressource1 = new Object();

    private static final Object ressource2 = new Object();

    private static final Object monitor = new Object();

    public static void main(String[] args) {
        while (!RConsole.isOpen()) {
            RConsole.open();
        }
        RealtimeThread t1 = new RealtimeThread(new PriorityParameters(19), new PeriodicParameters(new RelativeTime(6000, 0), new RelativeTime(50000, 0), new RelativeTime(50000, 0), new RelativeTime(50000, 0), null, null)) {

            public void run() {
                String name = "1 ";
                System.err.println(name);
                TimeReference.waitOneSecond();
                synchronized (ressource1) {
                    System.err.println(name);
                    TimeReference.waitOneSecond();
                    synchronized (ressource2) {
                        for (int i = 0; i < 5; i++) {
                            System.err.println(name);
                            TimeReference.waitOneSecond();
                        }
                    }
                    System.err.println(name);
                    TimeReference.waitOneSecond();
                }
            }
        };
        RealtimeThread t2 = new RealtimeThread(new PriorityParameters(18), new PeriodicParameters(new RelativeTime(8000, 0), new RelativeTime(50000, 0), new RelativeTime(50000, 0), new RelativeTime(50000, 0), null, null)) {

            public void run() {
                String name = "2 ";
                for (int i = 0; i < 2; i++) {
                    System.err.println(name);
                    TimeReference.waitOneSecond();
                }
            }
        };
        RealtimeThread t3 = new RealtimeThread(new PriorityParameters(17), new PeriodicParameters(new RelativeTime(3000, 0), new RelativeTime(50000, 0), new RelativeTime(50000, 0), new RelativeTime(50000, 0), null, null)) {

            public void run() {
                String name = "3 ";
                System.err.println(name);
                TimeReference.waitOneSecond();
                synchronized (ressource1) {
                    System.err.println(name);
                    TimeReference.waitOneSecond();
                    synchronized (ressource2) {
                        for (int i = 0; i < 5; i++) {
                            System.err.println(name);
                            TimeReference.waitOneSecond();
                        }
                    }
                    System.err.println(name);
                    TimeReference.waitOneSecond();
                }
            }
        };
        RealtimeThread t4 = new RealtimeThread(new PriorityParameters(16), new PeriodicParameters(new RelativeTime(1000, 0), new RelativeTime(50000, 0), new RelativeTime(50000, 0), new RelativeTime(50000, 0), null, null)) {

            public void run() {
                String name = "4 ";
                System.err.println(name);
                TimeReference.waitOneSecond();
                synchronized (ressource2) {
                    for (int i = 0; i < 4; i++) {
                        System.err.println(name);
                        TimeReference.waitOneSecond();
                    }
                }
            }
        };
        t4.start();
        t3.start();
        t1.start();
        t2.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
        }
        Thread tStopConsole = new Thread(new Runnable() {

            public void run() {
                RConsole.println("");
                RConsole.close();
            }
        });
        tStopConsole.setPriority(1);
        tStopConsole.start();
    }
}
