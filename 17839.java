import core.Core;
import mauri.utils.debug.DebugMessage;

public class JPhaseMainClass {

    public static void main(String[] args) throws Exception {
        DebugMessage.debuggingEnabled = true;
        Core.startJPhase();
    }
}
