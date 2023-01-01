import java.net.Socket;
import java.io.IOException;
import java.io.BufferedOutputStream;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;

/**
 * Integration tests to verify the correctness of the native I/O daemon control protocol.
 *
 * @author <a href = "mailto:juha@juhalindfors.com">Juha Lindfors</a>
 */
public class CommanProtocolTest {

    @BeforeClass(alwaysRun = true)
    public void init() {
        System.out.println("**** Starting daemon....");
        Daemon.start();
        try {
            Daemon.ping();
        } catch (Throwable t) {
            System.out.println("***********************************************************************");
            System.out.println("");
            System.out.println("  Cannot ping I/O daemon -- did it start or is it just very broken?");
            System.out.println("");
            System.out.println("  " + t.toString());
            System.out.println("");
            System.out.println("***********************************************************************");
            System.exit(-1);
        }
    }

    @Test
    public void unknownControlCommand() throws IOException {
        Daemon.sendBytes("DCONTROL0x00000007GARBAGE".getBytes());
    }

    @AfterClass(alwaysRun = true)
    public void destroy() {
        try {
            System.out.println("**** Stopping Daemon...\n");
            Daemon.stop();
        } catch (Throwable t) {
            System.out.println("**** Stopping failed - trying to kill...\n");
            Daemon.kill();
        }
    }
}
