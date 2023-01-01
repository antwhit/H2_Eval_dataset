import javax.swing.*;
import java.io.File;
import java.util.*;
import gnu.regexp.*;
import org.gjt.sp.jedit.*;
import org.gjt.sp.util.Log;

/**
 * A TemplateDir is a type of TemplateFile which is a container for other TemplateFiles. 
 * In this way we can create a tree of TemplateFiles similar to a directory tree or 
 * menu hierarchy.
 * @author Steve Jakob
 */
public class TemplateDir extends TemplateFile {

    private Hashtable templateFiles;

    private static RE backupFilter;

    public TemplateDir(File templateFile) {
        super(templateFile);
    }

    public boolean isDirectory() {
        return true;
    }

    /**
	 * Scans the templates directory and creates a Hashtable
	 * mapping template names to template files. Backup files are ignored
	 * based on the values of the backup prefix and suffix in the "Global
	 * Options" settings.
	 */
    public void refreshTemplates() {
        File f;
        this.templateFiles = new Hashtable();
        try {
            if (backupFilter == null) createBackupFilter();
            String[] files = this.templateFile.list();
            for (int i = 0; i < files.length; i++) {
                f = new File(this.templateFile, files[i]);
                if (f.isDirectory()) {
                    TemplateDir submenu = new TemplateDir(f);
                    this.templateFiles.put(files[i], submenu);
                    submenu.refreshTemplates();
                } else if (!backupFilter.isMatch(files[i])) {
                    TemplateFile tf = new TemplateFile(f);
                    this.templateFiles.put(files[i], tf);
                }
            }
        } catch (gnu.regexp.REException ree) {
            Log.log(Log.ERROR, this, jEdit.getProperty("plugin.TemplatesPlugin.error.bad-backup-filter"));
        }
    }

    private static void createBackupFilter() throws gnu.regexp.REException {
        String exp = jEdit.getProperty("backup.prefix") + "\\S+" + jEdit.getProperty("backup.suffix");
        backupFilter = new RE(exp, RE.REG_ICASE);
    }

    public void createMenus(JMenu menu) {
        Object o;
        JMenu submenu;
        JMenuItem mi;
        TemplateDir td;
        TemplateFile tf;
        Enumeration e;
        e = this.templateFiles.elements();
        while (e.hasMoreElements()) {
            o = e.nextElement();
            if (o instanceof TemplateDir) {
                td = (TemplateDir) o;
                submenu = new JMenu(td.getLabel());
                menu.add(submenu);
                td.createMenus(submenu);
            } else {
                tf = (TemplateFile) o;
                mi = new JMenuItem(tf.getLabel());
                mi.addActionListener(tf);
                menu.add(mi);
            }
        }
    }
}
