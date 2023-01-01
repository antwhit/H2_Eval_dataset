import java.io.Serializable;
import padmig.Migratory;
import pubweb.AbortedException;
import pubweb.IntegrityException;
import pubweb.bsp.BSPMigratable;
import pubweb.bsp.BSPMigratableProgram;
import pubweb.bsp.Message;

public class ArtificialWorkMig implements BSPMigratableProgram {

    @Migratory
    public void bspMain(BSPMigratable bsp, Serializable arg) throws AbortedException {
        if (!(arg instanceof String[])) {
            bsp.abort(new RuntimeException("String[] as arguments expected"));
        }
        String[] args = (String[]) arg;
        if (args.length != 6) {
            bsp.abort(new RuntimeException("Usage: ArtificialWork <# tests> <memory usage (in KB)> <# messages> <message size (in KB)> " + "<CPU usage (in 1000000 integer operations)> <CPU usage offset per pid (in 1000000 integer operations)>"));
        }
        @SuppressWarnings("unused") byte[] dummyData, msgData;
        int i, ii, nProcs, pid, nTests, memUsage, nMsgs, msgSize;
        long j, jj, nLoops, t1 = 0, t2;
        @SuppressWarnings("unused") Message msg;
        nProcs = bsp.getNumberOfProcessors();
        pid = bsp.getProcessId();
        nTests = Integer.parseInt(args[0]);
        memUsage = Integer.parseInt(args[1]) * 1024;
        nMsgs = Integer.parseInt(args[2]);
        msgSize = Integer.parseInt(args[3]) * 1024;
        nLoops = Long.parseLong(args[4]) + pid * Long.parseLong(args[5]);
        dummyData = new byte[memUsage];
        msgData = new byte[msgSize];
        bsp.syncMig();
        if (pid == 0) {
            t1 = bsp.getTime();
        }
        try {
            for (i = 0; i < nTests; i++) {
                for (ii = 0; ii < nMsgs; ii++) {
                    bsp.send((pid + 1) % nProcs, msgData);
                    if (i > 0) {
                        msg = bsp.getMessage(ii);
                    }
                }
                for (j = 0; j < nLoops; j++) {
                    for (jj = 0; jj < 1000000; jj++) {
                    }
                }
                bsp.syncMig();
                if (pid == 0) {
                    bsp.printStdOut("finished test " + (i + 1));
                }
            }
            for (ii = 0; ii < nMsgs; ii++) {
                msg = bsp.getMessage(ii);
            }
        } catch (IntegrityException ie) {
            bsp.abort(ie);
        }
        bsp.syncMig();
        if (pid == 0) {
            t2 = bsp.getTime();
            bsp.printStdOut("consumed time: " + ((t2 - t1) / 1000) + "s (" + nProcs + " procs, args: " + args[0] + " " + args[1] + " " + args[2] + " " + args[3] + " " + args[4] + " " + args[5] + ")");
        }
    }
}
