import org.vexi.framework.TestSessionServer;
import org.vexi.tpgame.TestTPGameServer;
import junit.framework.Test;
import junit.framework.TestSuite;

public class Test_TPGame {

    public static Test suite() {
        TestSuite suite = new TestSuite("Test tpgame");
        suite.addTestSuite(TestSessionServer.class);
        suite.addTestSuite(TestTPGameServer.class);
        return suite;
    }
}
