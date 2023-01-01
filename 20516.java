import java.io.PrintWriter;
import org.mortbay.log.Logger;
import org.mortbay.util.DateCache;

/** Jetty logger for our test webapp
 *
 *  @author <a href=mailto:claude.brisson.com>Claude Brisson</a>
 */
public class WebappLogger implements Logger {

    private boolean debug = true;

    private String name = null;

    private DateCache _dateCache = new DateCache("yyyy-MM-dd HH:mm:ss.SSS");

    private PrintWriter out = null;

    public WebappLogger() {
        this(null);
    }

    public WebappLogger(String name) {
        this.name = name == null ? "" : name;
        String context = System.getProperty("velosurf.test.webapp.log.context", "");
        String filename = System.getProperty("velosurf.test.webapp.log.file", "log/error.log");
        String dbg = System.getProperty("velosurf.test.webapp.log.debug", "off");
        debug = (dbg.equals("on") || dbg.equals("true"));
        if (context.equals(name)) {
            try {
                out = new PrintWriter(filename);
            } catch (Exception e) {
                System.err.println("could not log to file: " + filename);
            }
            logString("logger " + name + " initialized.");
        }
    }

    public boolean isDebugEnabled() {
        return debug;
    }

    public void setDebugEnabled(boolean enabled) {
        debug = enabled;
    }

    public void info(String msg, Object arg0, Object arg1) {
        if (out == null) return;
        if (msg.startsWith("loaded class") || msg.startsWith("loaded interface")) {
            return;
        }
        logString(_dateCache.now() + " " + format(msg, arg0, arg1));
    }

    public void debug(String msg, Throwable th) {
        if (debug) {
            if (out == null) return;
            if (msg.startsWith("loaded class") || msg.startsWith("loaded interface")) {
                return;
            }
            logString(_dateCache.now() + " " + msg);
            logStackTrace(th);
        }
    }

    public void debug(String msg, Object arg0, Object arg1) {
        if (debug) {
            if (out == null) return;
            if (msg.startsWith("loaded class") || msg.startsWith("loaded interface")) {
                return;
            }
            logString(_dateCache.now() + " " + format(msg, arg0, arg1));
        }
    }

    public void warn(String msg, Object arg0, Object arg1) {
        if (out == null) return;
        logString(_dateCache.now() + " " + format(msg, arg0, arg1));
    }

    public void warn(String msg, Throwable th) {
        if (out == null) return;
        logString(_dateCache.now() + " " + msg);
        logStackTrace(th);
    }

    public Logger getLogger(String name) {
        if ((name == null && this.name == null) || (name != null && name.equals(this.name))) return this;
        return new WebappLogger(name);
    }

    private synchronized void logString(String msg) {
        out.println(msg);
        out.flush();
    }

    private synchronized void logStackTrace(Throwable th) {
        th.printStackTrace(out);
        out.flush();
    }

    private String format(String msg, Object arg0, Object arg1) {
        int i0 = msg.indexOf("{}");
        int i1 = i0 < 0 ? -1 : msg.indexOf("{}", i0 + 2);
        if (arg1 != null && i1 >= 0) msg = msg.substring(0, i1) + arg1 + msg.substring(i1 + 2);
        if (arg0 != null && i0 >= 0) msg = msg.substring(0, i0) + arg0 + msg.substring(i0 + 2);
        return msg;
    }
}
