import iwork.eheap2.*;
import javax.speech.*;
import javax.speech.synthesis.*;

class Speaker {

    static void main(String[] args) {
        try {
            EventHeap theHeap = new EventHeap(args[0]);
            Event myEvent = new Event("AudioEvent");
            myEvent.setTemplateValue("AudioCommand", "Read");
            while (true) {
                try {
                    Event retEvent = theHeap.waitForEvent(myEvent);
                    simpleSpeak((String) (retEvent.getPostValue("Text")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void simpleSpeak(String phrase) {
        SynthesizerModeDesc mode = new SynthesizerModeDesc();
        Synthesizer synth = Central.createSynthesizer(mode);
        synth.allocate();
        synth.resume();
        synth.speakPlainText(phrase, null);
        synth.waitEngineState(Synthesizer.QUEUE_EMPTY);
    }
}
