import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import com.dinim.graphics.GraphicsContextSwingImpl;
import com.dinim.graphics.IGraphicsContext;

public class Canvasframe extends JFrame {

    public JComponent component = new JComponent() {
    };

    private Image imagebuffer;

    private IGraphicsContext canvas;

    public Canvasframe(int width, int height) {
        addComponentListener(new ComponentListener() {

            public void componentHidden(ComponentEvent e) {
            }

            public void componentMoved(ComponentEvent e) {
            }

            public void componentResized(ComponentEvent e) {
                imagebuffer = component.createImage(component.getWidth(), component.getHeight());
                canvas = new GraphicsContextSwingImpl(imagebuffer.getGraphics());
            }

            public void componentShown(ComponentEvent e) {
            }
        });
        setSize(width, height);
        add(component);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        imagebuffer = component.createImage(width, height);
        canvas = new GraphicsContextSwingImpl(imagebuffer.getGraphics());
    }

    public void clearCanvas() {
        imagebuffer = component.createImage(component.getWidth(), component.getHeight());
        canvas = new GraphicsContextSwingImpl(imagebuffer.getGraphics());
    }

    public void paintCanvas() {
        component.getGraphics().drawImage(imagebuffer, 0, 0, component);
    }

    public IGraphicsContext getCanvas() {
        return canvas;
    }
}
