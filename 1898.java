import java.util.concurrent.CountDownLatch;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import persister.distributed.ServerCommunicator;

@RunWith(Suite.class)
@SuiteClasses(value = { SimpleBench.class })
public class LocalBenchmarkTestAll {

    public static final int PORT = 5555;

    static Thread server;

    @BeforeClass
    public static void startServer() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        server = new Thread(new Runnable() {

            @Override
            public void run() {
                latch.countDown();
                ServerCommunicator.main(new String[] { "-directory", "./benchmark_project_files", "-port", String.valueOf(PORT) });
            }
        }, "AP Server");
        server.start();
        latch.await();
        Thread.sleep(3000);
    }

    @AfterClass
    public static void stopServer() {
        server.interrupt();
    }
}
