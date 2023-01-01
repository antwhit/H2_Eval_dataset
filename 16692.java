class t3GT3 {

    static final boolean FORCE_GC = false;

    static int NUMBER_OF_WORKERS;

    static Object syncher;

    static Object syncher2;

    static boolean sanity = false;

    public static native int nativeBlocking(int time);

    public static void main(String args[]) {
        if (args[0].equals("-quiet")) {
            args = new String[] { "1", "50", "2500", "1", "1" };
            sanity = true;
        }
        int time;
        syncher = new Object();
        syncher2 = new Object();
        NUMBER_OF_WORKERS = Integer.parseInt(args[0]);
        System.out.println("Testing threads with WORKERS =" + NUMBER_OF_WORKERS);
        long starttime, endtime;
        int arg1 = Integer.parseInt(args[1]);
        int arg2 = Integer.parseInt(args[2]);
        t3GTWorker2 w2 = null;
        System.loadLibrary("t3GT3");
        t3GTGC.runit(1, 10);
        if (NUMBER_OF_WORKERS == 0) {
            starttime = System.currentTimeMillis();
            for (int i = 0; i < arg1; i++) t3GTGC.runit(arg1, arg2);
            if (FORCE_GC) {
                System.gc();
            }
        } else {
            int waiters, wait_time;
            if (args.length > 4) {
                waiters = Integer.parseInt(args[3]);
                wait_time = Integer.parseInt(args[4]);
                System.out.println(waiters + " waiters, for time " + wait_time);
            } else waiters = wait_time = 0;
            BlockingWorker b[] = null;
            if (waiters != 0) {
                b = new BlockingWorker[waiters];
                for (int i = 0; i < waiters; i++) {
                    b[i] = new BlockingWorker(wait_time);
                    b[i].start();
                }
            }
            System.out.println(" Blocking workers started");
            t3GT3Worker a[] = new t3GT3Worker[NUMBER_OF_WORKERS];
            for (int wrk = 0; wrk < NUMBER_OF_WORKERS; wrk++) {
                a[wrk] = new t3GT3Worker(arg1, arg2);
                a[wrk].start();
            }
            for (int i = 0; i < NUMBER_OF_WORKERS; i++) {
                while (!a[i].isReady) {
                    try {
                        Thread.currentThread().sleep(100);
                    } catch (InterruptedException e) {
                    }
                }
            }
            starttime = System.currentTimeMillis();
            synchronized (syncher) {
                syncher.notifyAll();
            }
            for (int i = 0; i < NUMBER_OF_WORKERS; i++) {
                while (!a[i].isFinished) {
                    synchronized (syncher2) {
                        try {
                            syncher2.wait();
                        } catch (InterruptedException e) {
                        }
                    }
                }
            }
        }
        endtime = System.currentTimeMillis();
        if (sanity) System.out.println("DONE\n"); else System.out.println(" Execution Time = " + (endtime - starttime) + " ms.");
    }
}
