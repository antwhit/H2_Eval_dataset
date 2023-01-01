abstract class OPT_InstrumentationPlan {

    /**
   * Should the compiler insert basic block instrumentation 
   **/
    public boolean insert_basic_block_counters = false;

    /**
   * Should the compiler insert method invocation counters
   **/
    public boolean insert_method_invocation_counters = false;

    /**
   * Called before at the beginning of compilation
   */
    abstract void initInstrumentation(VM_Method method);

    /**
   * Called after compilation completes, but before method is executed
   */
    abstract void finalizeInstrumentation(VM_Method method);
}
