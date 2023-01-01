import com.ice.tar.TarInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.zip.ZipInputStream;

public abstract class ArchiveCommand {

    abstract Hashtable getDirectories(String archiveProtocol, String archivePath) throws IOException;

    abstract InputStream createInputStream(String path) throws IOException;

    public static ArchiveCommand getCommand(InputStream in) {
        if (in instanceof TarInputStream) {
            return new TarCommand((TarInputStream) in);
        } else if (in instanceof ZipInputStream) {
            return new ZipCommand((ZipInputStream) in);
        } else {
        }
        return null;
    }
}
