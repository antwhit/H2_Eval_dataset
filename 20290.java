import java.util.*;
import java.text.*;

public class Sort extends Thread {

    private int numThread;

    private int arraySize;

    private int original[], values[];

    private Vector<ThreadSort> worker;

    private TimeStamp ts;

    private boolean debug;

    private Thread t;

    public Sort() {
    }

    public Sort(int numThread, int arraySize, boolean debug, boolean thread) {
        this.debug = debug;
        this.numThread = numThread;
        this.arraySize = arraySize;
        worker = new Vector<ThreadSort>();
        ts = new TimeStamp();
        if (thread) {
            t = new Thread(this);
            t.start();
        } else {
            createArray();
            sortArray();
        }
    }

    public void run() {
        createArray();
        sortArray();
    }

    public void createArray() {
        values = new int[arraySize];
        original = new int[arraySize];
        Random r = new Random((long) 77777773);
        for (int i = 0; i < arraySize; original[i] = values[i++] = (r.nextInt() % 10000)) ;
    }

    public void sortArray() {
        if (numThread == 1) {
            ts.start();
            Arrays.sort(values);
            ts.stop();
            printResult();
        } else {
            int startIndex, localArraySize;
            ts.start();
            for (int tid = 0; tid < numThread; tid++) {
                startIndex = tid * (values.length / numThread);
                if (tid != (numThread - 1)) localArraySize = values.length / numThread; else localArraySize = (values.length / numThread) + values.length % numThread;
                worker.add(new ThreadSort(tid, values, startIndex, localArraySize));
            }
            try {
                for (int i = 0; i < numThread; i++) worker.elementAt(i).getThread().join();
            } catch (InterruptedException e) {
                System.out.println("Main thread Interrupted");
                e.printStackTrace();
            }
            int localSize = 0;
            for (int i = 0; i < numThread; i++) {
                merge(values, worker.elementAt(i).getLocalArray(), localSize, worker.elementAt(i).getLocalArray().length);
                localSize += worker.elementAt(i).getArraySize();
            }
            ts.stop();
            if (debug) {
                System.out.println("Done sorting the array.");
                printResult();
            }
        }
    }

    private void merge(int a[], int b[], int aSize, int bSize) {
        int i, j, k;
        int[] c = new int[a.length];
        for (i = 0; i < aSize; i++) c[i] = a[i];
        i = 0;
        j = 0;
        k = 0;
        while (i < aSize && j < bSize) {
            if (c[i] <= b[j]) a[k++] = c[i++]; else a[k++] = b[j++];
        }
        while (i < aSize) a[k++] = c[i++];
        while (j < bSize) a[k++] = b[j++];
    }

    private String outputFormat(int fieldSize, int intValue) {
        String inum = (new DecimalFormat("0")).format(intValue);
        return ("            ").substring(0, fieldSize - inum.length()) + inum;
    }

    private void printResult() {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        System.out.println("execution time for Sorting workload is: " + ts.elapsedTime() + " nanoseconds.");
        System.out.println();
    }

    public double getExecutionTime() {
        return this.ts.elapsedTime();
    }
}
