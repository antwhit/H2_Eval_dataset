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
public class AllSegmentIntersectionsCanvas extends SegmentIntersectionCanvas {

    private static final Color INTERSECTION_COLOR = Color.red;

    private boolean allIntersections = false;

    private Collection intersections;

    public AllSegmentIntersectionsCanvas(CGDesktop desktop, SegmentIntersection siTest) {
        super(desktop, siTest);
        intersections = new ArrayList();
    }

    public boolean isAllIntersectionsEnabled() {
        return allIntersections;
    }

    public void showAllIntersections() {
        allIntersections = true;
        update();
    }

    public void hideAllIntersections() {
        allIntersections = false;
        update();
    }

    /************************************************************
	 *                     CONCRETIZATION
	 ************************************************************/
    public void update() {
        if (allIntersections) {
            Collection pairs = test.allIntersections();
            Iterator i = pairs.iterator();
            intersections.clear();
            while (i.hasNext()) {
                Intersection in = (Intersection) i.next();
                RealShape s = (RealShape) in.getIntersections().get(0);
                intersections.add(s.toShape());
            }
        }
        repaint();
        if (allIntersections) try {
            test.checkPreconditions(SegmentIntersection.ALL_INTERSECTIONS);
        } catch (PreconditionViolatedException ex) {
            showPreconditionViolatedDialog(ex.getMessage());
        }
    }

    /************************************************************
	 *                        PAINTING
	 ************************************************************/
    public void paintComponent(Graphics gfx) {
        super.paintComponent(gfx);
        Graphics2D g2 = (Graphics2D) gfx;
        if (allIntersections) {
            g2.setColor(INTERSECTION_COLOR);
            Iterator i = intersections.iterator();
            while (i.hasNext()) {
                Shape s = (Shape) i.next();
                s.draw(g2);
            }
        }
    }
}
