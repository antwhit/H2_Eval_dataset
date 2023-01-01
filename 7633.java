import gui.GuiStartup;
import javax.swing.UIManager;
import org.apache.log4j.Logger;

public class Main {

    private static Logger log = Logger.getLogger("Main");

    public static void main(String[] args) {
        log.info("Programm gestartet");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.DbManager.initDB();
        new GuiStartup();
    }
}
