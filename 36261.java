import java.awt.*;
import javax.swing.*;

/**
 *
 * @author  gunter.zeilinger@tiani.com
 * @version 1.0.0
 */
public class ImageDisplayApplet extends JApplet {

    public void init() {
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(new ImageDisplay(this), BorderLayout.CENTER);
    }

    /** Creates a new instance of ImageDisplay */
    public ImageDisplayApplet() {
    }
}
