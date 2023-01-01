import java.io.File;

/**
 * FileFilter für den JFileChooser => Zeigt nur .cnc-Dateien und Verzeichnisse
 * im Öffnen- und Speicherndialog.
 * @author Holger Herbst
 */
public class FileFilter extends javax.swing.filechooser.FileFilter {

    /**
     * Erhält den Dateipfad und gibt zurück, ob die Bedingungen zutreffen.
     * @param file Dateipfad
     * @return  Ob die Bedingungen zutreffen (.cnc oder Vezeichnis)
     */
    public boolean accept(File file) {
        String filename = file.getName();
        return filename.endsWith(".cnc") || file.isDirectory();
    }

    /**
     * 
     * @return Die zulässige Dateiendung
     */
    public String getDescription() {
        return "*.cnc";
    }
}
