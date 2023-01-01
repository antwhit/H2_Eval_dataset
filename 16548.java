import java.util.Hashtable;
import java.util.Enumeration;

final class VM_YieldpointCounterData extends VM_StringEventCounterData implements VM_Reportable {

    static final boolean DEBUG = false;

    /**
   *  Constructor
   *
   * @manager The manager that will provide the counter space
   **/
    VM_YieldpointCounterData(OPT_InstrumentedEventCounterManager manager) {
        super(manager, "Yieldpoint Counter");
        automaticallyGrowCounters(true);
    }

    /**
   *  Called at end when data should dump it's contents.
   */
    public void report() {
        VM_Instrumentation.disableInstrumentation();
        VM.sysWrite("Printing " + dataName + ":\n");
        VM.sysWrite("--------------------------------------------------\n");
        double total = 0;
        double methodEntryTotal = 0;
        double backedgeTotal = 0;
        for (Enumeration e = stringToCounterMap.keys(); e.hasMoreElements(); ) {
            String stringName = (String) e.nextElement();
            Integer counterNum = (Integer) stringToCounterMap.get(stringName);
            double count = getCounter(counterNum.intValue());
            VM.sysWrite(count + " " + stringName + "\n");
            total += count;
            if (stringName.indexOf("METHOD ENTRY") != -1) methodEntryTotal += count;
            if (stringName.indexOf("BACKEDGE") != -1) backedgeTotal += count;
        }
        VM.sysWrite("Total backedges: " + backedgeTotal + "\n");
        VM.sysWrite("Method Entry Total: " + methodEntryTotal + "\n");
        VM.sysWrite("Total Yieldpoints: " + total + "\n");
    }
}
