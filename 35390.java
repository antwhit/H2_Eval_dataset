import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.Sequence;
import com.frinika.project.ProjectContainer;
import junit.framework.TestCase;

/**
 * @author Peter Johan Salomonsen
 */
public class TestTempoAfterImportSaveLoad extends TestCase {

    Sequence seq;

    float tempo = 114;

    int resolution = 480;

    protected void setUp() throws Exception {
        super.setUp();
        seq = new Sequence(Sequence.PPQ, resolution, 1);
        int mpq = (int) (60000000 / tempo);
        try {
            MetaMessage tempoMsg = new MetaMessage();
            tempoMsg.setMessage(0x51, new byte[] { (byte) (mpq >> 16 & 0xff), (byte) (mpq >> 8 & 0xff), (byte) (mpq & 0xff) }, 3);
            MidiEvent tempoEvent = new MidiEvent(tempoMsg, 0);
            seq.getTracks()[0].add(tempoEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testImportMidi() {
        try {
            ProjectContainer proj = new ProjectContainer(seq);
            assertEquals((int) tempo, (int) proj.getSequencer().getTempoInBPM());
            assertEquals(resolution, proj.getSequence().getResolution());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
