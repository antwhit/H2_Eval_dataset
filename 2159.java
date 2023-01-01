import JNI2HPM;
import java.lang.Math;

public class Test_JNI2HPM {

    private static final boolean debug = false;

    /**
   * Test driver.
   */
    public static void main(String argv[]) {
        final int sequentialAccess = 1;
        final int smallerCacheLineAccess = 2;
        final int smallCacheLineAccess = 4;
        final int mediumCacheLineAccess = 8;
        final int largeCacheLineAccess = 16;
        final int largerCacheLineAccess = 32;
        final int smallPageAccess = 256;
        final int mediumPageAccess = 1024;
        final int largePageAccess = 4096;
        final int largerPageAccess = 16384;
        final int largestPageAccess = 65536;
        double base = 2;
        double exp = 18;
        final int limit = (int) java.lang.Math.pow(base, exp);
        int[] a = new int[limit];
        if (debug) System.out.println("Test_JNI2HPM.main()");
        System.setProperty("HPM_EVENT_1", "5");
        System.setProperty("HPM_EVENT_2", "6");
        System.setProperty("HPM_EVENT_3", "1");
        JNI2HPM.init();
        accessByRectangles(sequentialAccess, limit, a);
        accessByRectangles(sequentialAccess, limit, a);
        accessByRectangles(sequentialAccess, limit, a);
        accessByRectangles(smallerCacheLineAccess, limit, a);
        accessByRectangles(smallCacheLineAccess, limit, a);
        accessByRectangles(mediumCacheLineAccess, limit, a);
        accessByRectangles(largeCacheLineAccess, limit, a);
        accessByRectangles(largerCacheLineAccess, limit, a);
        accessByRectangles(smallPageAccess, limit, a);
        accessByRectangles(mediumPageAccess, limit, a);
        accessByRectangles(largestPageAccess, limit, a);
    }

    static void accessByRectangles(int chunkSize, int limit, int[] a) {
        long startTime = System.currentTimeMillis();
        int n_chunks = limit / chunkSize;
        System.out.println("accessByRectangles(" + chunkSize + ", " + limit + ") # chunks " + n_chunks + " startTime " + startTime);
        JNI2HPM.hpmStopCounting();
        JNI2HPM.hpmResetCounters();
        JNI2HPM.hpmStartCounting();
        int accesses = 0;
        for (int i = 0; i < chunkSize; i++) {
            for (int j = i; j < limit; j += chunkSize) {
                for (int k = j; k < j + chunkSize && k < limit; k += chunkSize) {
                    a[k] = chunkSize;
                    accesses++;
                }
            }
        }
        JNI2HPM.dump();
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        System.out.println("accessByRectangles(" + chunkSize + ") accesses " + accesses + " endTime " + endTime + " totalTime " + time + " ms");
    }
}
