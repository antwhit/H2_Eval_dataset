import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The mail class of the Q IRC Bot.
 * @author Ozafy - ozafy@borknet.org - http://www.borknet.org
 */
public class DelPoints implements Runnable {

    private DBControl dbc;

    Timer timer;

    /**
     * Runnable programs need to define this class.
     */
    public void run() {
        int delay = 10 * 1000;
        ActionListener taskPerformer = new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                dbc.delPoints(20);
            }
        };
        timer = new Timer(delay, taskPerformer);
        timer.start();
    }

    /**
     * Set the tutorial
     */
    public DelPoints(DBControl dbc) {
        this.dbc = dbc;
    }

    public void stop() {
        timer.stop();
    }
}
