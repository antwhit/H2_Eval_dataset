import java.util.*;

class VM_YieldCounterListener extends VM_NullListener implements VM_Uninterruptible {

    /**
   * Constructor
   *
   * @param yieldThreshold  the threshold of when to call organizer
   */
    public VM_YieldCounterListener(int yieldThreshold) {
        this.yieldThreshold = yieldThreshold;
    }

    /** update();  This method is called when its time to record that a 
   * yield point has occurred.
   * @param whereFrom Was this a yieldpoint in a PROLOGUE, BACKEDGE, or
   *             EPILOGUE?
   */
    public void update(int whereFrom) {
        nYields++;
        if (nYields >= yieldThreshold) {
            synchronized (this) {
                if (nYields >= yieldThreshold) {
                    passivate();
                    notifyOrganizer();
                    totalYields += nYields;
                    nYields = 0;
                }
            }
        }
    }

    public void report() {
        VM.sysWrite("Yield points counted: " + totalYields);
    }

    private int yieldThreshold;

    private int nYields = 0;

    private int totalYields = 0;
}
