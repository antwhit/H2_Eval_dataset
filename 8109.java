public class CachingEvaluator {

    public int ServiceModePlay = 0;

    public int ServiceModeRecord = 1;

    public int ServiceModePlayAndRecord = 2;

    public int canRecord() {
        return 0;
    }

    public int canPlayAndRecord() {
        return 0;
    }

    public int createHandle(int handle) {
        return 0;
    }

    public void beginOutput(int serviceMode, int handle) {
    }

    public void endOutput() {
    }

    public void discardRecording(int handle) {
    }

    public void playRecording(int handle) {
    }
}
