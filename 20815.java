import java.util.Random;

public class DMArrayCopyDoubleTest extends Thread {

    private double[] privateArray;

    public static Object lock;

    public DMArrayCopyDoubleTest(int num) {
        privateArray = new double[num];
    }

    private native void nSetArray(double[] srcArray, double[] dstArray);

    public void setArray(double[] srcArray) {
        this.nSetArray(srcArray, this.privateArray);
    }

    public double getArrayElement(int index) {
        return (privateArray[index]);
    }

    public int getArrayLength() {
        return this.privateArray.length;
    }

    public void run() {
        int arrayLength;
        boolean pass = true;
        Random rd = new Random();
        arrayLength = getArrayLength();
        System.out.println();
        double[] doubleArray = new double[arrayLength];
        for (int i = 0; i < arrayLength; i++) doubleArray[i] = rd.nextDouble();
        setArray(doubleArray);
        for (int i = 0; i < arrayLength; i++) {
            if (getArrayElement(i) != doubleArray[i]) {
                pass = false;
                break;
            }
        }
        System.out.println();
        if (pass) System.out.println("PASS: DMArrayCopyDoubleTest, Data written and read were same"); else System.out.println("FAIL: DMArrayCopyDoubleTest, Data written and read were not same");
    }

    public static void main(String args[]) {
        Random rd = new Random();
        Object lck = new Object();
        DMArrayCopyDoubleTest.lock = lck;
        GcThread.lock = lck;
        GcThread gc = new GcThread();
        DMArrayCopyDoubleTest test = new DMArrayCopyDoubleTest(rd.nextInt(100));
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
