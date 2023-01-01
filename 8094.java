import java.io.DataInputStream;
import java.net.DatagramPacket;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import org.xiph.speex.spi.SpeexEncoding;

public class AudioPlayerTest {

    SourceDataLine line;

    public AudioPlayerTest() {
        try {
            AudioFormat aFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 8000.0f, 16, 2, 4, 8000.0f, false);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, aFormat);
            line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(aFormat);
        } catch (Exception e) {
            System.out.println("Line Exception" + e);
        }
    }

    public void playFile() {
        byte[] data = new byte[MicReader.BUFFER_SIZE];
        try {
            while (true) {
                DatagramPacket packet = new DatagramPacket(data, data.length);
                Ss.servSocket.receive(packet);
                line.start();
                System.out.println("read" + data);
                line.write(packet.getData(), 0, MicReader.BUFFER_SIZE);
            }
        } catch (Exception e) {
            System.out.println("Line Exception" + e);
        }
    }
}
