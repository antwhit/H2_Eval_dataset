import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * A class that filters save files.
 * @author Joni Toivanen (jomiolto@gmail.com)
 */
public class SaveFileFilter extends FileFilter {

    /**
	 * Description of the file filter.
	 */
    private String description = "Save File (.sav)";

    /**
	 * Checks whether the given file is a save file.
	 * @param f file to check
	 * @return true if the file is a save file
	 */
    public boolean accept(File f) {
        String name = f.getName();
        if (f.isDirectory()) return (true);
        if (name.matches(".*\\.[sS][aA][vV]$")) return true;
        return (false);
    }

    /**
	 * Returns the description of the file filter.
	 * @return the desciption
	 */
    public String getDescription() {
        return description;
    }
}
