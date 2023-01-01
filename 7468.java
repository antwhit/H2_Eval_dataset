import java.util.Random;

public class DMFieldRWLongTest extends Thread {

    public long publicVar;

    private long privateVar;

    protected long protectedVar;

    public static Object lock;

    public DMFieldRWLongTest() {
        publicVar = 0;
        privateVar = 0;
        protectedVar = 0;
    }

    public native void nSetValues(long v1, long v2, long v3);

    public native long[] nGetValues();

    public long[] getValues() {
        long[] longArray = { publicVar, privateVar, protectedVar };
        return longArray;
    }

    public void run() {
        boolean pass = true;
        Random rd = new Random();
        long[] var = { rd.nextLong(), rd.nextLong(), rd.nextLong() };
        System.out.println();
        nSetValues(var[0], var[1], var[2]);
        System.out.println();
        long[] longArray1 = getValues();
        long[] longArray2 = nGetValues();
        if (longArray1.length == longArray2.length) {
            for (int i = 0; i < longArray1.length; i++) {
                if (longArray1[i] != longArray2[i]) {
                    pass = false;
                    break;
                }
            }
        } else {
            pass = false;
        }
        System.out.println();
        if (pass) System.out.println("PASS: DMFieldRWLongTest, Data written and read were same"); else System.out.println("FAIL: DMFieldRWLongTest, Data written and read were not same");
    }

    public static void main(String args[]) {
        Object lck = new Object();
        DMFieldRWLongTest.lock = lck;
        GcThread.lock = lck;
        DMFieldRWLongTest test = new DMFieldRWLongTest();
        GcThread gc = new GcThread();
        gc.setPriority(test.getPriority() + 1);
        gc.start();
        test.start();
        try {
            test.join();
        } catch (Exception e) {
        }
        gc.interrupt();
    }
}
