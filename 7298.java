import java.util.Random;
import com.frinika.sequencer.model.tempo.TempoList;
import junit.framework.TestCase;

/**
 * 
 * 
 * 
 * Check that the tempo list mapping of tick to time  (and then back) is consistant.
 * 
 */
public class TestTempoList extends TestCase {

    public void testTempoList() {
        int ticksPerBeat = 100;
        TempoList list = new TempoList(ticksPerBeat, null);
        int N = 1000;
        for (long i = 0; i < N; i += 10) {
            list.add(i, 60.0);
        }
        list.add(0, 60);
        Random rand = new Random();
        double ticks[] = new double[N];
        double bpm[] = new double[N];
        for (int i = 0; i < 0; i++) {
            list.add((long) (rand.nextDouble() * N), 40 + rand.nextDouble() * 200);
        }
        for (int i = 0; i < N; i++) {
            double tick = (long) (rand.nextDouble() * N);
            double t = list.getTimeAtTick(tick);
            double t2 = list.getTickAtTime(t);
            assertTrue(Math.abs(tick - t2) < 1e-7);
        }
    }

    public void testTempo2() {
        int ticksPerBeat = 128;
        TempoList list = new TempoList(ticksPerBeat, null);
        list.add(0, 60);
        list.add(4 * ticksPerBeat, 120);
        for (int i = 0; i < 8; i++) {
            System.out.println(list.getTimeAtTick(ticksPerBeat * i));
        }
        assertTrue(Math.abs(list.getTimeAtTick(0)) < 1e-7);
        assertTrue(Math.abs(list.getTimeAtTick(ticksPerBeat) - 1.0) < 1e-7);
        assertTrue(Math.abs(list.getTimeAtTick(ticksPerBeat * 4) - 4.0) < 1e-7);
        assertTrue(Math.abs(list.getTimeAtTick(ticksPerBeat * 5) - 4.5) < 1e-7);
        assertTrue(Math.abs(list.getTimeAtTick(ticksPerBeat * 6) - 5.0) < 1e-7);
    }

    public void testTempo3() {
        int ticksPerBeat = 128;
        TempoList list = new TempoList(ticksPerBeat, null);
        list.add(0, 60);
        list.add(4 * ticksPerBeat, 120);
        list.getTickAtTime(4.0);
        for (int i = 0; i < 8; i++) {
            System.out.println(list.getTickAtTime(i));
        }
        assertTrue(Math.abs(list.getTickAtTime(0)) < 1e-7);
        assertTrue(Math.abs(list.getTickAtTime(1.0) - ticksPerBeat) < 1e-7);
        assertTrue(Math.abs(list.getTickAtTime(2.0) - 2 * ticksPerBeat) < 1e-7);
        assertTrue(Math.abs(list.getTickAtTime(3.0) - 3 * ticksPerBeat) < 1e-7);
        assertTrue(Math.abs(list.getTickAtTime(4.0) - 4 * ticksPerBeat) < 1e-7);
        assertTrue(Math.abs(list.getTickAtTime(5.0) - 6.0 * ticksPerBeat) < 1e-7);
    }

    public static void main(String args[]) {
        new TestTempoList().testTempoList();
        new TestTempoList().testTempo2();
        new TestTempoList().testTempo3();
    }
}
