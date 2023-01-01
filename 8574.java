import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.Image;
import java.io.IOException;

public class WelcomeScreen implements CommandListener {

    private Display display;

    private AuthScreen as;

    private PanMobile mobileApp;

    private Form welcomeMsg;

    private Command exitCommand;

    private Command authCommand;

    private Image welcomeImage;

    public WelcomeScreen(Display d, PanMobile pm) throws NullPointerException {
        if (d == null || pm == null) throw new NullPointerException();
        display = d;
        mobileApp = pm;
        as = null;
        try {
            welcomeImage = Image.createImage("/panoptes.png");
        } catch (IOException ioe) {
        }
        welcomeMsg = new Form("Welcome message");
        welcomeMsg.append("Welcome to Panopto-Mobile. Press start to begin\n");
        welcomeMsg.append(welcomeImage);
        exitCommand = new Command("Exit", Command.EXIT, 1);
        authCommand = new Command("Start", Command.SCREEN, 1);
        welcomeMsg.addCommand(exitCommand);
        welcomeMsg.addCommand(authCommand);
        welcomeMsg.setCommandListener(this);
    }

    public void showMe() {
        display.setCurrent(welcomeMsg);
    }

    public Form getCurrent() {
        return welcomeMsg;
    }

    public void commandAction(Command c, Displayable s) {
        if (c == exitCommand) {
            mobileApp.closeMidlet();
        } else if (c == authCommand) {
            if (as == null) as = new AuthScreen(display, this);
            as.showMe();
        }
    }
}
