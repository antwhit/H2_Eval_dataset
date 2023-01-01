import java.io.*;
import org.gjt.sp.jedit.msg.*;

public interface Handler {

    public void handleQuit();

    public void handleAbout();

    public void handlePrefs();

    public void handleOpenFile(File file);

    public void handleOpenFile(ViewUpdate msg);

    public void handleFileCodes(BufferUpdate msg);
}
