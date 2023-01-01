/**
 * Display per-thread cpu utilization information in real time.
 * @authorDerek Lieber
 * @date 09 Nov 1999 
 */
class CpuMonitor extends VM_Thread {

    static void main() {
        if (!VM.BuildForCpuMonitoring) {
            VM.sysWrite("CpuMonitor.main: vm wasn't built for cpu monitoring\n");
            return;
        }
        VM_Thread t = new CpuMonitor();
        t.isAlive = true;
        t.schedule();
    }

    private CpuMonitor() {
        makeDaemon(true);
    }

    public void run() {
        VM.sysWrite("Cpu monitor running\n");
        for (; ; ) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
            }
            VM_Processor.getCurrentProcessor().disableThreadSwitching();
            double totalCpuTime = 0;
            for (int threadIndex = 0, n = VM_Scheduler.threads.length; threadIndex < n; ++threadIndex) {
                VM_Thread t = VM_Scheduler.threads[threadIndex];
                if (t == null) continue;
                totalCpuTime += t.cpuTotalTime;
            }
            VM.sysWrite("\033[H\033[2J");
            for (int threadIndex = 0, n = VM_Scheduler.threads.length; threadIndex < n; ++threadIndex) {
                VM_Thread t = VM_Scheduler.threads[threadIndex];
                if (t == null) continue;
                int cpu = (int) (t.cpuTotalTime / totalCpuTime * 100);
                t.cpuTotalTime = 0;
                char cpu0 = (char) ('0' + cpu / 10);
                char cpu1 = (char) ('0' + cpu % 10);
                VM.sysWrite(+threadIndex + "(" + cpu0 + cpu1 + (t.isIdleThread ? "i" : t.isGCThread ? "g" : t.isDaemon ? "d" : "") + (!t.isAlive ? "!" : "") + (t.cpuStartTime > 0 ? "+" : "-") + ") ");
                VM.sysWrite("\n");
            }
            VM.sysWrite((int) (totalCpuTime * 1000) + "ms");
            VM.sysWrite("\n");
            VM_Processor.getCurrentProcessor().enableThreadSwitching();
        }
    }
}
