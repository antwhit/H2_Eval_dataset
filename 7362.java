import org.gjt.sp.jedit.*;

public class ConsoleShell implements Shell {

    public void printInfoMessage(Output output) {
        output.printInfo(jEdit.getProperty("console.shell.info"));
    }

    public void execute(View view, String command, Output output) {
    }

    public void stop() {
    }

    public boolean waitFor() {
        return true;
    }
}
