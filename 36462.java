import java.io.FilenameFilter;
import java.io.File;

public class ExtensionFilter implements FilenameFilter {

    private String[] extensions;

    public ExtensionFilter(String... extns) {
        extensions = extns;
    }

    public boolean accept(File dir, String name) {
        for (String ext : extensions) {
            if (name.endsWith(ext)) {
                return true;
            }
        }
        return false;
    }
}
