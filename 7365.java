import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.sound.sampled.LineUnavailableException;

public class FMCapture implements WindowListener {

    private static final float SAMPLE_RATE = 8000.0f;

    private static final int NUM_SAMPLES = 512;

    private static final int BUFFER_SIZE = 4096;

    private static final int SLEEP_TIME = 15;

    private static boolean running = true;

    public static void main(String[] args) throws InterruptedException, LineUnavailableException {
        SoundReader sr = new SoundReader(SAMPLE_RATE, BUFFER_SIZE);
        FrequencyReader fr = new FrequencyReader(sr);
        FrequencyDisplay fd = new FrequencyDisplay(NUM_SAMPLES);
        sr.start();
        while (running) {
            fd.updateFrequency(fr.read(NUM_SAMPLES));
            Thread.sleep(SLEEP_TIME);
        }
        fd.dispose();
        sr.pause();
        sr.join();
    }

    public void windowClosing(WindowEvent e) {
        running = false;
    }

    public void windowActivated(WindowEvent arg0) {
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowOpened(WindowEvent e) {
    }
}
