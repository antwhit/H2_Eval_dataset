/**
 * Thread in which a processor ("virtual cpu") begins its work.
 *
 * @author Bowen Alpern
 * @author Derek Lieber
 */
class VM_StartupThread extends VM_Thread {

    VM_StartupThread(int[] stack) {
        super(stack);
        makeDaemon(true);
    }

    public String toString() {
        return "VM_StartupThread";
    }

    public void run() {
        if (VM.TraceThreads) VM_Scheduler.trace("VM_StartupThread", "run");
        if (VM_Scheduler.cpuAffinity != VM_Scheduler.NO_CPU_AFFINITY) VM.sysVirtualProcessorBind(VM_Scheduler.cpuAffinity + VM_Processor.getCurrentProcessorId() - 1);
        VM_Processor.getCurrentProcessor().pthread_id = VM.sysCall0(VM_BootRecord.the_boot_record.sysPthreadSelfIP);
        if (VM.TraceThreads) VM_Scheduler.trace("VM_StartupThread", "pthread_id =", VM_Processor.getCurrentProcessor().pthread_id);
        VM_Processor.getCurrentProcessor().isInitialized = true;
        VM.sysWaitForVirtualProcessorInitialization();
        VM_Processor.getCurrentProcessor().enableThreadSwitching();
        VM.sysWaitForMultithreadingStart();
        if (VM.TraceThreads) VM_Scheduler.trace("VM_StartupThread", "terminating");
    }
}
