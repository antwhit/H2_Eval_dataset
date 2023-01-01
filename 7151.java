import java.io.*;
import java.util.*;
import java.awt.geom.*;
import com.iv.flash.api.*;
import com.iv.flash.api.shape.*;
import com.iv.flash.api.action.*;
import com.iv.flash.api.button.*;
import com.iv.flash.api.text.*;
import com.iv.flash.parser.*;
import com.iv.flash.util.*;

/**
 * Create a shape with gradient
 *
 */
public class JGExample6 extends JGExample {

    public static final int WIDTH = 100 * 20;

    public static final int HEIGHT = 25 * 20;

    public JGExample6() {
        FlashFile file = FlashFile.newFlashFile();
        Script script = new Script(1);
        script.setMain();
        file.setMainScript(script);
        Frame frame = script.newFrame();
        int x1 = 1000;
        int y1 = 1000;
        int x2 = 4000;
        int y2 = 4000;
        Shape shape = new Shape();
        shape.setLineStyle(new LineStyle(2 * 20, AlphaColor.black));
        Gradient g = new Gradient(0, AlphaColor.red);
        g.addTransition(255, AlphaColor.green);
        AffineTransform m = AffineTransform.getTranslateInstance(x1 + (x2 - x1) / 2, y1 + (y2 - y1) / 2);
        m.scale((x2 - x1) / 32768.0, (y2 - y1) / 32768.0);
        m.rotate(Math.PI / 4);
        shape.setFillStyle0(FillStyle.newLinearGradient(g, m));
        shape.drawRectangle(x1, y1, x2 - x1, y2 - y1);
        shape.setBounds(x1, y1, x2 - x1, y2 - y1);
        frame.addInstance(shape, 1, null, null);
        generate("example6.swf", file);
    }

    public static void main(String[] args) {
        init();
        JGExample ex = new JGExample6();
    }
}
