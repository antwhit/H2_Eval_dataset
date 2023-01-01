import lejos.nxt.LCD;
import lejos.nxt.comm.RConsole;
import lejos.realtime.PriorityParameters;
import lejos.realtime.RealtimeThread;
import lejos.realtime.RelativeTime;
import lejos.realtime.PeriodicParameters;
import lejos.util.TimeReference;

public class Test1Preemption {

    public static void main(String[] args) throws InterruptedException {
        while (!RConsole.isOpen()) {
            RConsole.open();
        }
        final Object monitor = new Object();
        RealtimeThread h = new RealtimeThread(new PriorityParameters(19), new PeriodicParameters(new RelativeTime(3000, 0), new RelativeTime(50000, 0), new RelativeTime(50000, 0), new RelativeTime(50000, 0), null, null)) {

            public void run() {
                String name = "1 ";
                for (int j = 0; j < 5; j++) {
                    synchronized (monitor) {
                        RConsole.print(name);
                    }
                    LCD.drawString(name + " - " + (1 + j), 1, 1);
                    TimeReference.waitOneSecond();
                }
            }
        };
        RealtimeThread b = new RealtimeThread(new PriorityParameters(17), new PeriodicParameters(new RelativeTime(1000, 0), new RelativeTime(50000, 0), new RelativeTime(50000, 0), new RelativeTime(50000, 0), null, null)) {

            public void run() {
                String name = "2 ";
                for (int j = 0; j < 5; j++) {
                    synchronized (monitor) {
                        RConsole.print(name);
                    }
                    LCD.drawString(name + " - " + (1 + j), 1, 1);
                    TimeReference.waitOneSecond();
                }
            }
        };
        b.start();
        h.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
        }
        Thread t = new Thread(new Runnable() {

            public void run() {
                RConsole.println("");
                RConsole.close();
            }
        });
        t.setPriority(1);
        t.start();
    }
}
