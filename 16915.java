import java.io.*;
import java.util.*;
import java.awt.geom.*;
import com.iv.flash.api.*;
import com.iv.flash.api.shape.*;
import com.iv.flash.parser.*;
import com.iv.flash.util.*;

/**
 * Draws a rectangle with a stroke
 *
 * Steps:
 *  - create file and main script
 *  - add first frame to the script
 *  - create shape
 *  - add fill and line styles to the shape
 *  - choose just added styles for drawing
 *  - draw rectangle
 *  - set bounding box
 *  - add instance (placeobject) of the shape to the frame
 *  - generate the file
 *
 */
public class JGExample1 extends JGExample {

    public JGExample1(int rect_size, int stroke_width, AlphaColor rect_color, AlphaColor stroke_color) {
        FlashFile file = FlashFile.newFlashFile();
        Script script = new Script(1);
        script.setMain();
        file.setMainScript(script);
        Frame frame = script.newFrame();
        Shape shape = new Shape();
        shape.setLineStyle(new LineStyle(stroke_width, stroke_color));
        shape.setFillStyle0(FillStyle.newSolid(rect_color));
        Rectangle2D r = GeomHelper.newRectangle(0, 0, rect_size, rect_size);
        shape.drawRectangle(r);
        shape.setBounds(r);
        frame.addInstance(shape, 1, new AffineTransform(), null);
        generate("example1.swf", file);
    }

    public static void main(String[] args) {
        init();
        JGExample ex = new JGExample1(50 * 20, 6 * 20, AlphaColor.red, AlphaColor.blue);
    }
}
