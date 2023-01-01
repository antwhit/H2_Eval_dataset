import pspdash.*;
import java.lang.reflect.InvocationTargetException;
import java.io.IOException;

public class setPath extends TinyCGIBase implements Runnable {

    /** Write the CGI header. */
    protected void writeHeader() {
        out.print("Expires: 0\r\n");
        super.writeHeader();
    }

    /** Generate CGI script output. */
    protected void writeContents() throws IOException {
        DashController.checkIP(env.get("REMOTE_ADDR"));
        try {
            javax.swing.SwingUtilities.invokeAndWait(this);
        } catch (InterruptedException ie) {
        } catch (InvocationTargetException ite) {
            if (ite.getTargetException() instanceof IOException) throw (IOException) ite.getTargetException();
        }
        DashController.printNullDocument(out);
    }

    public void run() {
        boolean startTiming = (parameters.get("start") != null);
        String phase = (String) parameters.get("phase");
        if (DashController.setPath(getPrefix()) == false) startTiming = false; else if (phase != null) {
            if (DashController.setPhase(phase) == false) startTiming = false;
        }
        if (startTiming) DashController.startTiming();
    }
}
