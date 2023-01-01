import java.util.Random;

public class DMArrayRWBodyLongTest extends Thread {

    private long[] privateArray;

    public static Object lock;

    public DMArrayRWBodyLongTest(int num) {
        privateArray = new long[num];
    }

    private native void nSetArray(long[] dstArray, long[] srcArray, int arrayLength);

    public void setArray(long[] srcArray) {
        this.nSetArray(this.privateArray, srcArray, getArrayLength());
    }

    public long[] getArray() {
        int arrLength = getArrayLength();
        long[] longArray = new long[arrLength];
        for (int i = 0; i < arrLength; i++) longArray[i] = privateArray[i];
        return longArray;
    }

    public int getArrayLength() {
        return this.privateArray.length;
    }

    public void run() {
        int arrayLength;
        boolean pass = true;
        Random rd = new Random();
        arrayLength = getArrayLength();
        long[] inArray = new long[arrayLength];
        for (int i = 0; i < arrayLength; i++) inArray[i] = rd.nextLong();
        setArray(inArray);
        long[] outArray = getArray();
        for (int i = 0; i < arrayLength; i++) {
            if (inArray[i] != outArray[i]) {
                pass = false;
                break;
            }
        }
        System.out.println();
        if (pass) System.out.println("PASS: DMArrayRWBodyLongTest, Data written and read were same"); else System.out.println("FAIL: DMArrayRWBodyLongTest, Data written and read were not same");
    }

    public static void main(String args[]) {
        Random rd = new Random();
        Object lck = new Object();
        DMArrayRWBodyLongTest.lock = lck;
        GcThread.lock = lck;
        GcThread gc = new GcThread();
        DMArrayRWBodyLongTest test = new DMArrayRWBodyLongTest(rd.nextInt(100));
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
