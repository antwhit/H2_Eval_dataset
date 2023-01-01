import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.URL;

public class ZoomControlView extends JPanel implements IView {

    CoreModel model;

    Icon zoom1to1Icon;

    Icon zoomInIcon;

    Icon zoomOutIcon;

    JButton zoomIn;

    JButton zoom1to1;

    JButton zoomOut;

    public ZoomControlView(CoreModel model) {
        assert model != null;
        this.model = model;
        this.layoutView();
        this.registerControllers();
    }

    public void layoutView() {
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        URL zoom100Url = getClass().getResource("icons/gtk-zoom-100.png");
        URL zoomInUrl = getClass().getResource("icons/gtk-zoom-in.png");
        URL zoomOutUrl = getClass().getResource("icons/gtk-zoom-out.png");
        Toolkit tk = Toolkit.getDefaultToolkit();
        try {
            Image zoom100Image = tk.createImage(zoom100Url);
            Image zoomInImage = tk.createImage(zoomInUrl);
            Image zoomOutImage = tk.createImage(zoomOutUrl);
            zoom1to1Icon = new ImageIcon(zoom100Image);
            zoomInIcon = new ImageIcon(zoomInImage);
            zoomOutIcon = new ImageIcon(zoomOutImage);
        } catch (Exception e) {
            System.err.println(e.getCause());
        }
        this.zoomOut = new JButton(zoomOutIcon);
        this.zoom1to1 = new JButton(zoom1to1Icon);
        this.zoomIn = new JButton(zoomInIcon);
        this.zoomOut.setToolTipText("Zoom Out");
        this.zoom1to1.setToolTipText("Zoom to 1:1");
        this.zoomIn.setToolTipText("Zoom In");
        this.add(Box.createHorizontalGlue());
        this.add(zoomOut);
        this.add(zoom1to1);
        this.add(zoomIn);
    }

    public void updateView() {
    }

    public void registerControllers() {
        this.zoomIn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                model.incZoomWidth();
            }
        });
        this.zoom1to1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                model.zoomWidth1to1();
            }
        });
        this.zoomOut.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                model.decZoomWidth();
            }
        });
    }
}
