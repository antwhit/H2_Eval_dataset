import org.jikesrvm.scheduler.VM_Scheduler;

class BlockingThreadsWorker extends Thread {

    static final boolean trace = false;

    int sleepTime;

    boolean isFinished;

    BlockingThreadsWorker(int time) {
        this.sleepTime = time;
        this.isFinished = false;
    }

    public void start() {
        super.start();
    }

    public void run() {
        int loopctr = 5;
        if (trace) VM_Scheduler.trace("Worker", "hello - time", sleepTime);
        for (int i = 0; i < loopctr; i++) {
            if (trace) VM_Scheduler.trace("Worker", "calling nativeBlocking for time = ", sleepTime);
            tBlockingThreads.nativeBlocking(sleepTime);
            if (trace) VM_Scheduler.trace("Worker", "returned from nativeBlocking for time = ", sleepTime);
        }
        if (trace) VM_Scheduler.trace("Worker", "bye - time", sleepTime);
        isFinished = true;
    }
}
