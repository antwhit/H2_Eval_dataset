import codesounding.SilentProcessor;
import codesounding.jsyn.SimpleFader;
import com.softsynth.jsyn.LineOut;
import com.softsynth.jsyn.SampleReader_16F1;
import com.softsynth.jsyn.Synth;
import com.softsynth.jsyn.SynthSample;

public class SampleProcessor2 extends SilentProcessor {

    private LineOut lineOut;

    SynthSample mySamp;

    SampleReader_16F1 mySampler;

    final int NUM_FRAMES = 64;

    short[] data;

    SimpleFader numFramesFader;

    private int oldNumFrames;

    boolean firstTime = true;

    public SampleProcessor2() {
        Synth.startEngine(0);
        oldNumFrames = NUM_FRAMES;
        mySamp = new SynthSample(oldNumFrames);
        data = new short[oldNumFrames];
        mySampler = new SampleReader_16F1();
        lineOut = new LineOut();
        mySampler.output.connect(0, lineOut.input, 0);
        mySampler.output.connect(0, lineOut.input, 1);
        lineOut.start();
        mySampler.start();
        numFramesFader = SimpleFader.openFader("Edit", "Num frames", oldNumFrames, 1, 200);
    }

    private synchronized void ping(int amplitude) {
        int currentNumFrame = (int) numFramesFader.getValue();
        if (currentNumFrame != oldNumFrames) {
            oldNumFrames = currentNumFrame;
            mySamp = new SynthSample(oldNumFrames);
            data = new short[oldNumFrames];
        }
        for (int i = 0; i < oldNumFrames; i++) {
            if (i == 0) {
                data[i] = (short) (amplitude * 4);
            } else {
                data[i] = 0;
            }
        }
        mySamp.write(data);
        mySampler.samplePort.queue(mySamp);
    }

    public void getVarDeclaration() {
        ping(1000);
    }

    public void getStartBlock() {
        ping(2000);
    }

    public void getEndBlock() {
        ping(3000);
    }

    public void getIfStatement() {
        ping(4000);
    }

    public void getForStatement() {
        ping(5000);
    }

    public void getDoStatement() {
        ping(6000);
    }

    public void getWhileStatement() {
        ping(7000);
    }

    public void getReturnStatement() {
        ping(8000);
    }

    public void getBreakStatement() {
        ping(11000);
    }

    public void getContinueStatement() {
        ping(9000);
    }

    public void getThrowStatement() {
        ping(10000);
    }
}
