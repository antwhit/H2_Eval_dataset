import java.util.Random;

public class DMArrayCopyByteTest extends Thread {

    private byte[] privateArray;

    public static Object lock;

    public DMArrayCopyByteTest(int num) {
        privateArray = new byte[num];
    }

    private native void nSetArray(byte[] srcArray, byte[] dstArray);

    public void setArray(byte[] srcArray) {
        this.nSetArray(srcArray, this.privateArray);
    }

    public byte getArrayElement(int index) {
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
        byte[] byteArray = new byte[arrayLength];
        rd.nextBytes(byteArray);
        setArray(byteArray);
        for (int i = 0; i < arrayLength; i++) {
            if (getArrayElement(i) != byteArray[i]) {
                pass = false;
                break;
            }
        }
        System.out.println();
        if (pass) System.out.println("PASS: DMArrayCopyByteTest, Data written and read were same"); else System.out.println("FAIL: DMArrayCopyByteTest, Data written and read were not same");
    }

    public static void main(String args[]) {
        Random rd = new Random();
        Object lck = new Object();
        DMArrayCopyByteTest.lock = lck;
        GcThread.lock = lck;
        GcThread gc = new GcThread();
        DMArrayCopyByteTest test = new DMArrayCopyByteTest(rd.nextInt(100));
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
