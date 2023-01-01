import net.sourceforge.jdbconverter.ScanDatabase;

/**
 * @author Volker Berlin
 */
public class JDbScan {

    /**
     * The start pont for scanning a database.
     * @param args the command line parameters.
     */
    public static void main(String[] args) {
        new ScanDatabase().start(args);
    }
}
