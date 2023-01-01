import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Diese Klasse muss noch weitere Methoden enthalten wie setEntry
 * zum Beispiel. Dies ist ein fettes TODO nachdem die Grundstruktur 
 * steht.
 * @author Steffen Springer
 *
 */
public class Menu {

    private static JMenuBar jm;

    private static int PROJECT = 1;

    private static int EXPERTICE = 2;

    private static int HELP = 3;

    private static int Preprocessing = 0;

    /**
	 * Initialization of MenuBar. At this point, all Plugins
	 * in directory got to be loaded for menu entries
	 * Last-Edit : 20060925 DS 
	 */
    public static JMenuBar setUpMenu() {
        if (jm != null) {
        } else {
            JMenuBar jm = new JMenuBar();
            JMenu project = new JMenu("Project");
            project.add(new JMenuItem("New..."));
            project.add(new JMenuItem("Load..."));
            project.add(new JMenu("Recent"));
            project.addSeparator();
            project.add(new JMenuItem("Save"));
            project.addSeparator();
            project.add(new JMenuItem("Print"));
            project.add(new JMenuItem("Properties"));
            jm.add(project);
            JMenu protocol = new JMenu("Protocol");
            protocol.add("New Protocol");
            protocol.addSeparator();
            protocol.add(new JMenuItem("Insert Chapter"));
            protocol.add(new JMenuItem("Insert Resultarea"));
            protocol.add(new JMenuItem("Insert From File"));
            protocol.addSeparator();
            protocol.add(new JMenuItem("Remove Chapter"));
            protocol.add(new JMenuItem("Remove Resultarea"));
            jm.add(protocol);
            JMenu methods = new JMenu("Methods");
            JMenu preproc = new JMenu("Preprocessing");
            preproc.add(new JMenuItem("Mean"));
            preproc.add(new JMenuItem("KillNaN"));
            JMenu clust = new JMenu("Clustering");
            clust.add(new JMenuItem("KMeans"));
            clust.add(new JMenuItem("DBScan"));
            methods.add(new JMenuItem("LoadData"));
            methods.add(preproc);
            methods.add(clust);
            methods.add(new JMenu("Classification"));
            methods.add(new JMenu("Validation"));
            methods.add(new JMenu("Misc"));
            jm.add(methods);
            JMenu appMenu = new JMenu("Application");
            JMenuItem plugitem = new JMenuItem("PluginManager");
            appMenu.add(plugitem);
            appMenu.addSeparator();
            appMenu.add(new JMenuItem("Settings"));
            jm.add(appMenu);
            JMenu help = new JMenu("Help");
            help.add(new JMenuItem("Search"));
            help.add(new JMenuItem("About"));
            jm.add(help);
            Menu.jm = jm;
        }
        return jm;
    }

    private static JMenuItem createMenuItem(String name, ActionListener al) {
        JMenuItem t = new JMenuItem(name);
        t.addActionListener(al);
        return t;
    }

    public static void setEntry(int category, String name, ActionListener al) {
        jm.getMenu(1 + category).add(createMenuItem(name, al));
    }

    public static void main(String[] args) {
    }
}
