import java.io.File;
import java.io.FilenameFilter;

class MP3Filter implements FilenameFilter {

    public boolean accept(File dir, String name) {
        return (name.endsWith(".mp3"));
    }
}
