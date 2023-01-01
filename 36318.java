import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import java.lang.reflect.*;
import java.util.*;

public class ImageDialog extends BaseDialog {

    public class ImagePanel extends JPanel {

        private Image image = null;

        public ImagePanel(Server server, String imageName) {
            super(new BorderLayout());
            image = new ImageIcon(server.LoadResourceFile(imageName)).getImage();
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int width = getWidth();
            int height = getHeight();
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f));
            for (int i = 0; i < getHeight(); i += 4) {
                g2.setPaint(new Color(108, 165, 123));
                g2.fillRect(0, i, getWidth(), 2);
                g2.setPaint(new Color(82, 146, 105));
                g2.fillRect(0, i + 2, getWidth(), 2);
            }
            g2.dispose();
        }
    }

    ;

    TitleBar titleBar;

    public void setTitle(String title) {
        titleBar.setTitle(title);
    }

    public ImageDialog(JFrame parent, Server server, boolean modal) {
        super(parent, modal);
        ImagePanel image = new ImagePanel(server, "header.png");
        image.setPreferredSize(new Dimension(100, 0));
        getOuterPanel().add(image, BorderLayout.WEST);
        titleBar = new TitleBar(null, null, server);
        getOuterPanel().add(titleBar, BorderLayout.NORTH);
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                initDialog();
            }
        });
    }

    public void initDialog() {
    }
}
