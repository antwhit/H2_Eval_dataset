import computational.*;
import computational.geometry.*;
import computational.geometry.segmentintersection.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author Massimo Bartoletti
 * @version 1.1
 */
public abstract class SegmentIntersectionCanvas extends CGCanvas {

    protected SegmentIntersection test;

    private int nextLabel = 0;

    public SegmentIntersectionCanvas(CGDesktop desktop, SegmentIntersection siTest) {
        super(desktop);
        this.test = siTest;
        addShapeListener(new ShapeAdapter() {

            public void shapeAdded(Shape shape) {
                if (shape instanceof Segment) {
                    Segment s = (Segment) shape;
                    s.setLabel("s" + nextLabel++);
                    test.addSegment(s);
                    update();
                }
            }

            public void shapeMoved(Shape shape, int dx, int dy) {
                if (shape instanceof Segment) {
                    test.moveSegment((Segment) shape, dx, dy);
                    update();
                }
            }

            public void shapeDeleted(Shape shape) {
                if (shape instanceof Segment) {
                    test.deleteSegment((Segment) shape);
                    update();
                }
            }
        });
        addSegmentListener(new SegmentListener() {

            public void pointMoved(Segment s, Point p, int dx, int dy) {
                test.movePoint(s, p, dx, dy);
                update();
            }
        });
    }

    /************************************************************
	 *                     CONCRETIZATION
	 ************************************************************/
    public Collection getShapes() {
        return test.getSegments();
    }

    public void setShapes(Collection shapes) throws InvalidCanvasException {
        Iterator i = shapes.iterator();
        while (i.hasNext()) {
            Shape shape = (Shape) i.next();
            if (shape instanceof Segment) {
                Segment s = (Segment) shape;
                s.setLabel("s" + nextLabel++);
                test.addSegment(s);
            } else throw new InvalidCanvasException();
        }
        update();
    }

    public void clear() {
        test.clear();
        nextLabel = 0;
        update();
    }
}
