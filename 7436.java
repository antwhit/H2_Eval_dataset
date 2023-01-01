import org.nakedobjects.object.NakedObject;
import org.nakedobjects.viewer.cli.View;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class SpeechSynthesizer implements View {

    private Voice helloVoice;

    private final View wrappedView;

    public SpeechSynthesizer(View wrappedView) {
        this.wrappedView = wrappedView;
    }

    public void clear() {
        wrappedView.clear();
        say("Ha HUM");
    }

    public void connect() {
        wrappedView.connect();
        VoiceManager voiceManager = VoiceManager.getInstance();
        Voice[] voices = voiceManager.getVoices();
        for (int i = 0; i < voices.length; i++) {
            System.out.println("    " + voices[i].getName() + " (" + voices[i].getDomain() + " domain)");
        }
        String voiceName = "kevin16";
        helloVoice = voiceManager.getVoice(voiceName);
        if (helloVoice == null) {
            System.err.println("Cannot find a voice named " + voiceName + ".  Please specify a different voice.");
            System.exit(1);
        }
        helloVoice.allocate();
    }

    public void disconnect() {
        wrappedView.disconnect();
        helloVoice.deallocate();
    }

    public void display(String message) {
        wrappedView.display(message);
        say(message);
    }

    public void prompt(String string) {
        wrappedView.prompt(string);
        say(string);
    }

    public void displayEntry(String entry) {
    }

    public void display(NakedObject object) {
        wrappedView.display(object);
        say(object.titleString());
    }

    public void error(String message) {
        wrappedView.error(message);
        say("ERROR; " + message);
    }

    private void say(String text) {
        helloVoice.speak(text);
    }
}
