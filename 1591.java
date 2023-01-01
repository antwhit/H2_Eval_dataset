import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Ansicht eines Bildes in Originalgr��e.
 *
 * @author $Author:  Bastian Martinschledde & Ren� Stakemeier
 * @version $Revision:  $, $Date:  $ UTC
 */
@SuppressWarnings("serial")
public class PicturePopupDialog extends JDialog {

    /**
    * 
    * Konstruktor. �bernimmt anzuzeigendes Bild
    * 
    * @param icon Bild
    */
    public PicturePopupDialog(Icon icon) {
        if (icon == null) {
            throw new NullPointerException("icon is null");
        }
        setTitle("Originalgr��e");
        setSize(icon.getIconWidth(), icon.getIconHeight());
        setResizable(false);
        JPanel content = new JPanel();
        content.add(new JLabel(icon));
        add(content);
    }
}
