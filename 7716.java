import javax.microedition.midlet.*;
import javax.microedtion.lcdui.*;

/**
 * This simple file shows various features of AmebaEngine.
 * Take a look at config.jh for more details.
 *
 * @author Maciej Kocemba
 * @date Wed Apr 28 10:19:37 2004
 */
public class MyGame extends MIDlet implements CommandListener {

    Command iCmdExit;

    TextBox iText;

    private void testBuiltInDefines() {
        System.out.println("Source file   : .\\test\\ref\\MyGame.java");
        System.out.println("Source line   : 31");
        System.out.println("File          : .\\test\\nokia_s40_en\\MyGame.java");
        System.out.println("Line          : 28");
        System.out.println("Date          : Tue Nov 25 17:30:10 CET 2003");
        System.out.println("Ameba version : 2.1.0_10");
    }

    public MyGame() {
        iCmdExit = new Command("Exit", Command.EXIT, 1);
        iText = new TextBox("MyGame", "Hello World!", 20, TextField.ANY);
        iText.addCommand(iCmdExit);
        iText.setCommandListener(this);
    }

    protected void startApp() {
        Display.getDisplay(this).setCurrent(iText);
    }

    protected void pauseApp() {
    }

    protected void destroyApp() {
    }

    public void commandAction(Command aCmd, Displayable aDisp) {
        if (aCmd == iCmdExit) {
            destroyApp(false);
            notifyDestroyed();
        }
    }
}
