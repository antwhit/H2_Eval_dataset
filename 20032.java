import java.util.*;
import org.gjt.sp.jedit.*;
import common.threads.WorkerThreadPool;

/**
 *Description of the Class
 *
 * @author     svu
 * @created    16 �������� 2001 �.
 */
public class CommonControlsPlugin extends EditPlugin {

    public void stop() {
        WorkerThreadPool.getSharedInstance().shutdown();
    }
}
