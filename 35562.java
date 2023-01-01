class Worker extends Thread {

    private String name;

    volatile boolean readyFlag = false;

    volatile boolean doneFlag = false;

    Object theLock;

    int rc;

    /**
   * Constructor
   */
    Worker(String name, Object lockObject) {
        this.name = name;
        theLock = lockObject;
        readyFlag = false;
        doneFlag = false;
    }

    public void start() {
        super.start();
    }

    public void run() {
        readyFlag = true;
        MonitorTest.printVerbose(".... " + name + " ready to start");
        while (!MonitorTest.startCounting) {
        }
        MonitorTest.printVerbose(".... " + name + " calling native monitor");
        rc = MonitorTest.accessMonitorFromNative(theLock);
        if (rc != 0) MonitorTest.setFailFlag();
        MonitorTest.printVerbose(".... " + name + " done.");
        doneFlag = true;
    }
}
