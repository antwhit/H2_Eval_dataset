import java.awt.*;
import javax.swing.*;

public class LogoPanel extends JPanel {

    private final int WIDTH = 200, HEIGHT = 65;

    private ImageIcon image;

    public LogoPanel() {
        setBackground(Color.black);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        image = new ImageIcon("./logos/monopoly_brand_logo_small.jpg");
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        image.paintIcon(this, g, 31, 11);
    }
}
