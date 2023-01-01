import java.io.File;
import org.acplt.oncrpc.apps.jportmap.OncRpcEmbeddedPortmap;
import org.acplt.oncrpc.apps.jportmap.jportmap;
import org.openthinclient.mountd.Exporter;
import org.openthinclient.mountd.ListExporter;
import org.openthinclient.mountd.MountDaemon;
import org.openthinclient.mountd.NFSExport;
import org.openthinclient.nfsd.NFSServer;
import org.openthinclient.nfsd.PathManager;

public class NFSServerMain {

    public static void main(String[] args) throws Exception {
        System.err.print("Checking for PORTMAP Server...");
        System.err.flush();
        jportmap pm = null;
        if (OncRpcEmbeddedPortmap.isPortmapRunning()) {
            System.err.println("FOUND");
        } else {
            System.err.println("NOT FOUND");
            try {
                System.err.println("Starting PORTMAP Server");
                pm = new jportmap();
                final jportmap p = pm;
                new Thread("portmapper") {

                    @Override
                    public void run() {
                        try {
                            System.err.println("Starting portmapper");
                            p.run(p.transports);
                            System.err.println("portmapper exited");
                        } catch (Throwable th) {
                            th.printStackTrace();
                            System.err.println("portmapper");
                            System.exit(1);
                        }
                    }
                }.start();
            } catch (Throwable th) {
                th.printStackTrace();
                System.err.println("Failed to start PORTMAP Server");
                System.exit(1);
            }
        }
        NFSExport e[] = new NFSExport[1];
        e[0] = new NFSExport("/share", new File("share").getAbsoluteFile());
        final Exporter exporter = new ListExporter(e);
        final PathManager pathManager = new PathManager(new File("nfs-handles.db"), exporter);
        final NFSServer nfs = new NFSServer(pathManager, 0, 0);
        new Thread("NFS server") {

            @Override
            public void run() {
                try {
                    System.err.println("Starting NFS Server");
                    nfs.run();
                    System.err.println("NFS Server exited");
                } catch (Throwable th) {
                    th.printStackTrace();
                    System.err.println("NFS Server failed");
                    System.exit(1);
                }
            }
        }.start();
        final MountDaemon mountd = new MountDaemon(pathManager, exporter, 0, 0);
        new Thread("MOUNT daemon") {

            @Override
            public void run() {
                setName("MOUNT Server");
                try {
                    System.err.println("Starting MOUNT Server");
                    mountd.run();
                    System.err.println("MOUNT Server exited");
                } catch (Throwable th) {
                    th.printStackTrace();
                    System.err.println("MOUNT Server failed");
                    System.exit(1);
                }
            }
        }.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        nfs.stopRpcProcessing();
        mountd.stopRpcProcessing();
        pm.stopRpcProcessing();
        System.out.println("I'm gone!");
    }
}
