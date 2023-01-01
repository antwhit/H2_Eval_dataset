import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;

public final class BTRocklet extends MIDlet implements CommandListener {

    private BTRocker rocker;

    protected static Command exit = new Command("exit", Command.EXIT, 0);

    protected static Command pause = new Command("pause", Command.ITEM, 1);

    public BTRocklet() {
        rocker = new BTRocker(this, pause, exit);
    }

    public void startApp() {
        rocker.connect();
        Display.getDisplay(this).setCurrent(rocker);
    }

    protected void destroyApp(boolean unconditional) {
        rocker.destroy();
    }

    protected void pauseApp() {
        rocker.disconnect();
    }

    public void commandAction(Command c, Displayable d) {
        if (c == exit) {
            destroyApp(true);
            notifyDestroyed();
            return;
        }
        if (c == pause) {
            pauseApp();
            notifyPaused();
            return;
        }
    }
}
