import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.swing.JFrame;
import junit.framework.TestCase;
import com.frinika.project.ProjectContainer;
import com.frinika.project.settings.projectsettingsversions.Project20050227;
import com.frinika.sequencer.FrinikaSequence;
import com.frinika.synth.SynthRack;
import com.frinika.synth.synths.MySampler;
import com.frinika.voiceserver.VoiceServer;

/**
 * @author Peter Johan Salomonsen
 */
public class ProjectCompatibilityTest extends TestCase {

    ProjectContainer proj;

    float tempo = 120f;

    protected void setUp() throws Exception {
        FrinikaSequence sequence = new FrinikaSequence(Sequence.PPQ, 128, 1);
        int mpq = (int) (60000000 / tempo);
        MetaMessage tempoMsg = new MetaMessage();
        tempoMsg.setMessage(0x51, new byte[] { (byte) (mpq >> 16 & 0xff), (byte) (mpq >> 8 & 0xff), (byte) (mpq & 0xff) }, 3);
        MidiEvent tempoEvent = new MidiEvent(tempoMsg, 0);
        sequence.getFrinikaTrackWrappers().get(0).add(tempoEvent);
        ByteArrayOutputStream sequenceOutputStream = new ByteArrayOutputStream();
        MidiSystem.write(sequence, 1, sequenceOutputStream);
        Project20050227 project = new Project20050227();
        project.setSequence(sequenceOutputStream.toByteArray());
        SynthRack synthRack = new SynthRack(new VoiceServer() {

            @Override
            public void configureAudioOutput(JFrame frame) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        MySampler sampler = new MySampler(synthRack);
        synthRack.setSynth(1, sampler);
        project.setSynthSettings(synthRack.getSynthSetup());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(baos);
        out.writeObject(project);
        proj = ProjectContainer.loadProject(new ByteArrayInputStream(baos.toByteArray()));
    }

    public void testTempo() throws Exception {
        proj.getSequencer().start();
        Thread.sleep(100);
        proj.getSequencer().stop();
        assertEquals((int) tempo, (int) proj.getSequencer().getTempoInBPM());
    }
}
