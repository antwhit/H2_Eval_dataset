import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Picture extends JPanel {

    private static final long serialVersionUID = 1L;

    Image m_image;

    Picture(ImageProducer ip) {
        Image colorImage = createImage(ip);
        int w = colorImage.getWidth(this);
        int h = colorImage.getHeight(this);
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
        Graphics g = image.getGraphics();
        g.drawImage(colorImage, 0, 0, null);
        g.dispose();
        m_image = image;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(m_image, 0, 0, this);
        g.setColor(Color.red);
        int w = m_image.getWidth(this);
        int h = m_image.getHeight(this);
        g.drawLine(w, 0, w, h);
        g.drawLine(0, h, w, h);
        System.out.println("h = " + h + " w = " + w);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Test");
        int h = 512;
        int w = 512;
        int[] pixels = new int[w * h];
        for (int i = 0; i < w * h; ++i) {
            pixels[i] = 0xFF000000 + (int) (Math.random() * 255);
        }
        MemoryImageSource ip = new MemoryImageSource(w, h, pixels, 0, w);
        Picture mw = new Picture(ip);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(mw);
        f.setSize(1024, 1024);
        f.setVisible(true);
    }
}
