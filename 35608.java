/**
 * Test monitor operation from native
 * The following JNI calls are tested:
 *      MonitorEnter         MonitorExit
 *
 * @author Ton Ngo, Steve Smith 
 * @date 1/3/01
 */
class MonitorTest {

    static boolean verbose = true;

    static boolean allTestPass = true;

    static volatile boolean startCounting = false;

    static int globalCount = 0;

    static String stringObject;

    public static native void setVerboseOff();

    /**
   * native methods that will call the JNI Monitor functions
   */
    static native int accessMonitorFromNative(Object lockObject);

    /**
   * called from native, increment a count protected by a lock on stringObject in native 
   */
    static void accessCountUnderNativeLock(int increment) {
        synchronized (stringObject) {
            globalCount += increment;
        }
    }

    static synchronized void setFailFlag() {
        allTestPass = false;
    }

    /**
   * constructor initializes instance fields
   */
    public MonitorTest() {
    }

    public static void main(String args[]) {
        int returnValue;
        System.loadLibrary("MonitorTest");
        if (args.length != 0) {
            if (args[0].equals("-quiet")) {
                verbose = false;
                setVerboseOff();
            }
        }
        stringObject = new String("Lock me");
        printVerbose("Creating worker threads");
        Worker threadOne = new Worker("thread One", stringObject);
        Worker threadTwo = new Worker("thread Two", stringObject);
        printVerbose("Starting worker threads");
        threadOne.start();
        threadTwo.start();
        while (!threadOne.readyFlag || !threadTwo.readyFlag) {
        }
        printVerbose("Worker threads running, start counting");
        startCounting = true;
        while (!threadOne.doneFlag || !threadTwo.doneFlag) {
        }
        printVerbose("Worker threads finish, check count");
        int copyCount = globalCount;
        if (copyCount == 0 && allTestPass) {
            System.out.println("PASS: MonitorTest");
        } else {
            System.out.println("FAIL: MonitorTest");
            printVerbose("Expect globalCount = 0, get " + copyCount);
        }
    }

    static void printVerbose(String str) {
        if (verbose) System.out.println(str);
    }
}
