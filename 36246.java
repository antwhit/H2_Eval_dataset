/**
 * VM_Instrumentation.java
 *
 * This class is used to provide general functionality useful to
 * instrumenting methods.
 *
 * @author Matthew Arnold
 *
*/
final class VM_Instrumentation {

    /**
   * A pointer to a OPT_InstrumentedEventCounterManager, (See
   * VM_InstrumentedEventCounterManager.java for the idea behind a
   * counter manager) There can be multiple managers in use at the
   * same time (for example, one per method)., but for now we just use
   * one for everything.
   **/
    public static OPT_InstrumentedEventCounterManager eventCounterManager;

    /**
   * Called at boot time
   **/
    static void boot(VM_AOSOptions options) {
        if (options.INSERT_INSTRUCTION_COUNTERS || options.INSERT_METHOD_COUNTERS_OPT || options.INSERT_YIELDPOINT_COUNTERS || options.INSERT_DEBUGGING_COUNTERS) {
            eventCounterManager = new VM_CounterArrayManager();
        }
        if (options.INSERT_METHOD_COUNTERS_OPT) {
            VM_AOSDatabase.methodInvocationCounterData = new VM_MethodInvocationCounterData(eventCounterManager);
            VM_AOSDatabase.methodInvocationCounterData.automaticallyGrowCounters(true);
            VM_RuntimeMeasurements.registerReportableObject(VM_AOSDatabase.methodInvocationCounterData);
        }
        if (options.INSERT_YIELDPOINT_COUNTERS) {
            VM_AOSDatabase.yieldpointCounterData = new VM_YieldpointCounterData(eventCounterManager);
            VM_RuntimeMeasurements.registerReportableObject(VM_AOSDatabase.yieldpointCounterData);
        }
        if (options.INSERT_INSTRUCTION_COUNTERS) {
            VM_AOSDatabase.instructionCounterData = new VM_StringEventCounterData(eventCounterManager, "Instruction Counter");
            VM_AOSDatabase.instructionCounterData.automaticallyGrowCounters(true);
            VM_RuntimeMeasurements.registerReportableObject(VM_AOSDatabase.instructionCounterData);
        }
        if (options.INSERT_DEBUGGING_COUNTERS) {
            VM_AOSDatabase.debuggingCounterData = new VM_StringEventCounterData(eventCounterManager, "Debugging Counters");
            VM_AOSDatabase.debuggingCounterData.automaticallyGrowCounters(true);
            VM_RuntimeMeasurements.registerReportableObject(VM_AOSDatabase.debuggingCounterData);
        }
    }

    /**
   * Calling this routine causes all future compilations not to insert
   * instrumentation, regardless of what the options say.  Used during
   * system shutdown.  Note, this method will not stop instrumentation
   * in currently compiled methods from executing.
   * 
   */
    static void disableInstrumentation() {
        instrumentationEnabled = false;
    }

    /**
   * Enable instrumentations, so that future compilations will not
   * perform any instrumentation.
   * 
   */
    static void enableInstrumentation() {
        instrumentationEnabled = true;
    }

    /**
   * Is it currently O.K. to compile a method and insert instrumentation?
   */
    static boolean instrumentationEnabled() {
        return instrumentationEnabled;
    }

    private static boolean instrumentationEnabled = true;
}
