/**
 * A collector thread which does not participate in GC, useful for
 * <code>VM_Processor</code>s which should not participate in GC.
 *
 * @author Manu Sridharan
 * @author Tony Cocchi
 * @author Stephen Smith
 */
class VM_PassiveCollectorThread extends VM_Thread implements VM_Uninterruptible {

    private static final boolean trace = false;

    public void run() {
        VM_Processor myProcessor = VM_ProcessorLocalState.getCurrentProcessor();
        VM_Magic.pragmaNoOptCompile();
        VM_Thread.getCurrentThread().jniEnv.JNITopJavaFP = VM_Magic.getFramePointer();
        int processorStatusIndex = VM_Processor.getCurrentProcessor().vpStatusIndex;
        while (true) {
            VM_Scheduler.passiveCollectorMutex.lock();
            VM_Thread.getCurrentThread().yield(VM_Scheduler.passiveCollectorQueue, VM_Scheduler.passiveCollectorMutex);
            myProcessor.disableThreadSwitching();
            if (trace) {
                VM_Scheduler.trace("VM_PassiveCollectorThread", "waking up - setting vpStatus -> IN_SIGWAIT");
                VM_Scheduler.trace("VM_PassiveCollectorThread", "waking up - about to sigWait");
            }
            int TOC = 0;
            TOC = VM_BootRecord.the_boot_record.sysTOC;
            VM_Magic.sysCallSigWait(VM_BootRecord.the_boot_record.sysPthreadSigWaitIP, TOC, myProcessor.vpStatusAddress, VM_Processor.IN_SIGWAIT, VM_Thread.getCurrentThread().contextRegisters);
            VM_Processor.vpStatus[processorStatusIndex] = VM_Processor.IN_JAVA;
            VM_ProcessorLocalState.setCurrentProcessor(myProcessor);
            myProcessor.enableThreadSwitching();
        }
    }

    static VM_PassiveCollectorThread createPassiveCollectorThread(int[] stack, VM_Processor processorAffinity) {
        return new VM_PassiveCollectorThread(stack, processorAffinity);
    }

    private VM_PassiveCollectorThread(int[] stack, VM_Processor processorAffinity) {
        super(stack);
        makeDaemon(true);
        this.isPassiveCollectorThread = true;
        this.processorAffinity = processorAffinity;
    }
}
