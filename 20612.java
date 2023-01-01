import java.util.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import org.audiocomplex.gps.*;
import org.audiocomplex.util.*;

public class Jeo extends MIDlet implements LoggerInterface, StreamerListener, CommandListener {

    public static Jeo app;

    private Display display;

    private Timer timer = new Timer();

    private Streamer streamer;

    private Compass compass = new Compass();

    private Navigator nav = new Navigator(timer, compass);

    private String[] log = new String[10];

    private int log_tail = 0;

    public Jeo() {
        app = this;
        display = Display.getDisplay(this);
        Logger.register(this);
        log("hello world");
    }

    protected void startApp() {
        display.setCurrent(nav);
    }

    protected void pauseApp() {
    }

    protected void destroyApp(boolean unconditional) {
    }

    public void exit() {
        destroyApp(false);
        notifyDestroyed();
    }

    public void flash() {
        display.flashBacklight(100);
    }

    public void foreground() {
        display.setCurrent(display.getCurrent());
    }

    public void background() {
        display.setCurrent(null);
    }

    public void alert(String s) {
        display.setCurrent(new Alert("Info", s, null, AlertType.INFO));
    }

    public void selectGpsDevice() {
        display.setCurrent(new BluetoothSelector(this));
    }

    public void commandAction(Command c, Displayable d) {
        if (c == BluetoothSelector.SELECT || c == BluetoothSelector.CANCEL) {
            display.setCurrent(nav);
        }
    }

    public void startstop() {
        try {
            if (streamer == null) {
                String url = VFS.setup().getString("gps-url");
                if (url == null) {
                    alert("Bitte zuerst GPS-Ger�t w�hlen");
                    return;
                }
                streamer = new Streamer(url, timer);
                streamer.addListener(this);
                streamer.start();
            } else {
                streamer.dispose();
                streamer = null;
            }
        } catch (Exception e) {
            alert(e.toString());
        }
    }

    public void started() {
        NMEALocationProvider p = new NMEALocationProvider(streamer);
        p.addListener(nav);
        p.addListener(compass);
        nav.started();
    }

    public void stopped() {
        nav.stopped();
    }

    public void received(String s) {
    }

    public void log(String s) {
        log[log_tail] = s;
        log_tail = (log_tail + 1) % log.length;
    }

    public String log() {
        String s = "";
        for (int i = 0; i < log.length; i++) {
            String l = log[(i + log_tail) % log.length];
            if (l != null) s += l + "\n";
        }
        return s;
    }
}
