import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.audio.JavaClipAudioPlayer;
import com.sun.speech.freetts.en.us.CMULexicon;

/**
 * Simple program to demonstrate the use of the FreeTTS speech synthesizer.
 */
public class FreeTTSHelloWorld {

    public static void main(String[] args) {
        try {
            String voiceClassName = (args.length > 0) ? args[0] : "com.sun.speech.freetts.en.us.CMUDiphoneVoice";
            Class voiceClass = Class.forName(voiceClassName);
            Voice helloVoice = (Voice) voiceClass.newInstance();
            helloVoice.setLexicon(new CMULexicon());
            helloVoice.setAudioPlayer(new JavaClipAudioPlayer());
            helloVoice.load();
            helloVoice.speak("Thank you for giving me a voice. I'm so glad to say hello to this world.");
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
