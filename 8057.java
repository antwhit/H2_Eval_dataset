import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import org.jdesktop.jdic.filetypes.Association;
import org.jdesktop.jdic.filetypes.AssociationService;
import org.jdesktop.jdic.icons.IconService;

/**
 * JDIC API demo class.
 * <p>
 * The class represents an disk object.
 */
public class DiskObject {

    private static AssociationService associationService = new AssociationService();

    public static final int TYPE_DRIVER = 1;

    public static final int TYPE_FOLDER = 2;

    public static final int TYPE_FILE = 3;

    String name;

    private ImageIcon image;

    private String size;

    private String type;

    private String time;

    public DiskObject(File file, int fileType) {
        switch(fileType) {
            case TYPE_DRIVER:
                name = file.getAbsolutePath();
                image = FileExplorer.driverIcon;
                type = "Driver";
                break;
            case TYPE_FOLDER:
                name = file.getName();
                image = FileExplorer.folderIcon;
                type = "Folder";
                break;
            case TYPE_FILE:
                name = file.getName();
                size = MyUtility.length2KB(file.length());
                final Association assoc = getAssociation(file);
                if (assoc != null) {
                    type = assoc.getMimeType();
                    image = getIcon(assoc.getIconFileName());
                }
                if (type == null) type = "File";
                if (image == null) image = FileExplorer.fileIcon;
                break;
        }
        time = (new java.util.Date(file.lastModified())).toString();
    }

    private static Map iconCache = new HashMap();

    private static ImageIcon getIcon(final String iconSpec) {
        ImageIcon rc = (ImageIcon) iconCache.get(iconSpec);
        if (rc == null) {
            if (iconSpec != null) {
                final java.awt.Image i = IconService.getIcon(iconSpec, 16);
                rc = i != null ? new ImageIcon(i) : FileExplorer.fileIcon;
            }
            iconCache.put(iconSpec, rc);
        }
        return rc;
    }

    private Association getAssociation(File file) {
        int dot = name.lastIndexOf('.');
        if (dot >= 0) {
            final String ext = name.substring(dot + 1);
            Association fileAssoc = associationService.getFileExtensionAssociation(ext);
            if (fileAssoc != null) return fileAssoc;
        }
        try {
            return associationService.getAssociationByContent(file.toURL());
        } catch (java.net.MalformedURLException ex) {
            return null;
        }
    }

    public String toString() {
        return name;
    }

    void renderIcon(JLabel label) {
        label.setText(name);
        label.setHorizontalAlignment(JLabel.LEFT);
        label.setIcon(image);
    }

    void renderSize(JLabel label) {
        label.setText(size);
        label.setHorizontalAlignment(JLabel.RIGHT);
        label.setIcon(null);
    }

    void renderType(JLabel label) {
        label.setText(type);
        label.setHorizontalAlignment(JLabel.LEFT);
        label.setIcon(null);
    }

    void renderModified(JLabel label) {
        label.setText(time);
        label.setHorizontalAlignment(JLabel.LEFT);
        label.setIcon(null);
    }
}
