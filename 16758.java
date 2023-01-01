/**
 * Test Back Edge GC
 *
 *    The classes in thi sfile test the back edge call to GC
 *
 *
 *
 * An object of this class loops creates a Call GC object
 *     then loops until a GC is done by a different object
 *        then it starts the GC object that it created
 *
 * @author unascribed
 */
class Looper extends Thread {

    static int gccomplete = 0;

    static int running = 0;

    static int initCount = 10000;

    int counter = 0;

    Looper(int cnt) {
        this.counter = cnt;
    }

    public void run() {
        running = 1;
        int r = running;
        int c = counter;
        int d = Looper.initCount;
        System.out.println("Looper - running");
        CallGC gc = new CallGC(2);
        System.out.println("Looper second CallGC created");
        while ((c > 0) && (gccomplete == 0)) {
            c--;
            if (c == 1) {
                r++;
                c = d;
            }
        }
        System.out.println("Looper second CallGC about to start");
        gc.start();
        System.out.println("Looper second CallGC complete- exiting Looper");
    }
}

/**
 * Objects of this class invokes GC and exit
 */
class CallGC extends Thread {

    int id = 0;

    CallGC(int cnt) {
        this.id = cnt;
    }

    public void run() {
        System.out.println("CallGC - running id = " + id);
        System.out.println("CallGC - calling GC id = " + id);
        System.gc();
        System.out.println("CallGC - GC complete- id = " + id);
        Looper.gccomplete = id;
        System.out.println("CallGC - run exiting id = " + id);
    }
}

/**  
 * Create the Looper object and start 
 *   Create the GC object
 *   wait until the looper object is started
 *     start the GC object
 *      wait for the GC object to complete, then exit
 *  
 */
class TestBackEdgeGC {

    public static void main(String args[]) throws java.lang.InterruptedException {
        System.out.println("TestBackEdgeGC-main  entered - starting Looper");
        int cnt = 10000;
        Looper.running = 0;
        Looper looper = new Looper(cnt);
        System.out.println("TestBackEdgeGC-creating CallGC");
        CallGC callGC = new CallGC(1);
        System.out.println("TestBackEdgeGC-run Looper");
        looper.start();
        while (Looper.running == 0) {
            try {
                Thread.currentThread().sleep(20);
            } catch (InterruptedException e) {
            }
        }
        System.out.println("TestBackEdgeGC-Looper running -starting CallGC");
        callGC.start();
        System.out.println("TestBackEdgeGC-waiting for join with callGC");
        callGC.join();
        System.out.println("TestBackEdgeGC-main: bye");
    }
}
