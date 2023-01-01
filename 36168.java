import junit.framework.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.image.*;
import java.awt.geom.*;

/** Test class for medicineTool.
 * @author TerpPaint
 * @version 2.0
 */
public class medicineToolTest extends TestCase {

    /** Constructor for medicineToolTest.
     * @param testName is used to create a medicineToolTest object.
     */
    public medicineToolTest(java.lang.String testName) {
        super(testName);
    }

    /** Runs the test class for medicineTool.
     * @param args the command line argument.
     */
    public static void main(java.lang.String[] args) {
        junit.swingui.TestRunner.run(medicineToolTest.class);
    }

    /** Makes a TestSuite for medicineTool.
     * @return suite returns the test results.
     */
    public static Test suite() {
        TestSuite suite = new TestSuite(medicineToolTest.class);
        return suite;
    }

    /** Test of clickAction method, of class medicineTool. */
    public void testClickAction() {
        System.out.println("testClickAction");
        medicineTool myTool = new medicineTool();
        MouseEvent myEvent;
        JPanel p = new JPanel();
        assertNotNull(p);
        MouseEvent e2 = new MouseEvent(p, MouseEvent.MOUSE_CLICKED, 0, 0, 1, 1, 1, false);
        main_canvas myCanvas = new main_canvas();
        myCanvas.main_image = new BufferedImage(255, 255, BufferedImage.TYPE_INT_RGB);
        myCanvas.setLeftColor(Color.blue);
        myCanvas.setRightColor(Color.red);
        myTool.clickAction(e2, myCanvas);
        assertNotNull(myTool.getMyColor());
        System.out.println("done testClickAction");
    }

    /** Test of dragAction method, of class medicineTool. */
    public void testDragAction() {
        System.out.println("testDragAction");
        medicineTool myTool = new medicineTool();
        MouseEvent myEvent;
        JPanel p = new JPanel();
        assertNotNull(p);
        MouseEvent e2 = new MouseEvent(p, MouseEvent.MOUSE_CLICKED, 0, 0, 200, 200, 1, false);
        main_canvas myCanvas = new main_canvas();
        myCanvas.setLeftColor(Color.blue);
        myCanvas.setRightColor(Color.red);
        myTool.dragAction(e2, myCanvas);
        System.out.println("done testDragAction");
    }

    /** Test of mouseReleaseAction method, of class medicineTool. */
    public void testMouseReleaseAction() {
        System.out.println("testMouseReleaseAction");
        medicineTool myTool = new medicineTool();
        MouseEvent myEvent;
        assertNotNull(myTool);
        JPanel p = new JPanel();
        assertNotNull(p);
        MouseEvent e2 = new MouseEvent(p, MouseEvent.MOUSE_CLICKED, 0, 0, 800, 800, 1, false);
        main_canvas myCanvas = new main_canvas();
        myCanvas.setLeftColor(Color.blue);
        myCanvas.setRightColor(Color.red);
        myTool.mouseReleaseAction(e2, myCanvas);
        System.out.println("done testMouseReleaseAction");
    }

    /** Test of getMyColor method, of class medicineTool. */
    public void testGetMyColor() {
        System.out.println("testGetMyColor");
        medicineTool myTool = new medicineTool();
        assertNotNull(myTool);
        MouseEvent myEvent;
        JPanel p = new JPanel();
        assertNotNull(p);
        MouseEvent e2 = new MouseEvent(p, MouseEvent.MOUSE_CLICKED, 0, 0, 200, 200, 1, false);
        main_canvas myCanvas = new main_canvas();
        myCanvas.setLeftColor(Color.blue);
        myCanvas.setRightColor(Color.red);
        Color color = myTool.getMyColor();
        System.out.println("done testGetMyColor");
    }
}
