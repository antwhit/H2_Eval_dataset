import java.io.Serializable;
import padmig.Migratory;
import pubweb.AbortedException;
import pubweb.IntegrityException;
import pubweb.bsp.BSPMigratable;
import pubweb.bsp.BSPMigratableProgram;

public class BackupTest implements BSPMigratableProgram {

    @Migratory
    public void bspMain(BSPMigratable bsp, Serializable args) throws AbortedException {
        for (int i = 0; i < 10; i++) {
            bsp.printStdOut("Falling asleep #" + i + " for 30s on " + bsp.getHostname() + "!");
            try {
                Thread.sleep(30000L);
            } catch (InterruptedException ie) {
            }
            try {
                bsp.send((bsp.getProcessId() + 1) % bsp.getNumberOfProcessors(), new Integer(42));
            } catch (IntegrityException ie) {
                bsp.printStdErr("an error occurred during 'send': " + ie.getMessage());
            }
            bsp.syncMig();
            bsp.printStdOut("received in total " + bsp.getAllMessages().length + " messages");
        }
    }
}
