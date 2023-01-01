import jdsl.core.algo.traversals.*;
import jdsl.core.api.*;
import java.awt.*;
import java.awt.geom.*;

/** 
 * The first step in drawing the tree. The tree is drawn so:
 *   - Edges are straight lines
 *   - nodes are centered above the drawings of their children
 *   - bounding boxes of the drawings of subtrees rooted at siblings are drawn
 *     adjacent.
 *   - The width of a bounding box is the maximum of the label width and the
 *     sum of the bounding boxes of the children.
 *
 * @author Lucy Perry (lep)
 * @version JDSL 2
*/
public class BoundingBoxCalculator extends EulerTour {

    protected int offset = 50;

    protected int width = 0;

    protected int depth = 0;

    protected Graphics g;

    protected FontMetrics fm;

    protected void visitFirstTime(Position pos) {
        pos.set("y", new Integer(depth));
        pos.set("x", new Integer(width));
        depth += offset;
    }

    protected void visitLastTime(Position pos) {
        int textWidth = textWidth(pos);
        int shift = 0;
        int x = ((Integer) pos.get("x")).intValue();
        int boxWidth = width - x;
        if (textWidth > boxWidth) {
            int delta = textWidth - boxWidth;
            boxWidth = textWidth;
            width += delta;
            shift = delta / 2;
        }
        pos.set("width", new Integer(boxWidth));
        pos.set("shift", new Integer(shift));
        depth -= offset;
    }

    protected void visitExternal(Position pos) {
        pos.set("y", new Integer(depth));
        pos.set("x", new Integer(width));
        int textWidth = textWidth(pos);
        width += textWidth;
        pos.set("width", new Integer(textWidth));
        pos.set("shift", new Integer(0));
    }

    protected int pad = 5;

    public BoundingBoxCalculator(Graphics gg) {
        g = gg;
        fm = g.getFontMetrics();
    }

    /**
   * Calculates the width of the drawing of the node label.  Stores 
   * the attributes needed to calculate the exact position to draw the
   * label.
   */
    protected int textWidth(Position pos) {
        String str = pos.element().toString();
        Rectangle2D bounds = fm.getStringBounds(str, g);
        pos.set("bounds", bounds);
        pos.set("ascent", new Integer(fm.getMaxAscent()));
        pos.set("descent", new Integer(fm.getMaxDescent()));
        return (int) bounds.getWidth() + pad;
    }
}
