import java.applet.Applet;
import javax.swing.JFrame;
import org.apache.log4j.Logger;
import app.*;
import gui.*;
import model.*;

/**
 * The main class for launching Euclide software.
 * 
 * @author dlegland
 */
public class Euclide extends Applet {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /** Apache log4j Logger */
    private static Logger logger = Logger.getLogger("Euclide");

    @Override
    public void init() {
        logger.info("Start applet Euclide");
        build(new String[0]);
    }

    public static void main(String[] args) {
        logger.info("Start programm Euclide");
        build(args);
    }

    private static void build(String[] args) {
        EuclideApp appli = new EuclideApp();
        logger.info("Create GUI");
        EuclideGui gui = new EuclideGui(appli);
        appli.addApplicationListener(gui);
        logger.info("Create Main frame");
        JFrame frame = gui.getCurrentFrame();
        frame.setVisible(true);
        logger.info("Initialize document");
        for (EuclideDoc doc : appli.getDocuments()) {
            doc.addDocumentListener(gui);
            for (EuclideSheet sheet : doc.getSheets()) {
                sheet.addSheetListener(gui);
                for (EuclideLayer layer : sheet.getLayers()) layer.addLayerListener(gui);
            }
        }
    }
}
