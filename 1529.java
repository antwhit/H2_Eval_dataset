import junit.framework.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.image.*;
import java.awt.geom.*;
import com.sun.image.codec.jpeg.*;

/** Test class for zoomTool.
 * @author TerpPaint
 */
public class SplashWindowTest extends TestCase {

    /** Constructor for SplashWindowTest.
     * @param testName String
     */
    public SplashWindowTest(java.lang.String testName) {
        super(testName);
    }

    /** Runs the test class for zoomTool.
     * @param args String[]
     */
    public static void main(java.lang.String[] args) {
        junit.swingui.TestRunner.run(SplashWindowTest.class);
    }

    /** Makes a TestSuite for zoomTool.
     * @return TestSuite
     */
    public static Test suite() {
        TestSuite suite = new TestSuite(SplashWindowTest.class);
        return suite;
    }

    /** Test of constructor method. */
    public void testConstructor() {
        System.out.println("testConstructor");
        JFrame f = new JFrame("hi");
        Image splashIm;
        try {
            File greg = new File("./images/paint_splash.jpeg");
            JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(new FileInputStream(greg));
            splashIm = decoder.decodeAsBufferedImage();
            SplashWindow sw = new SplashWindow(f, splashIm, 473, 373);
            assertEquals(sw.horizontal, 473);
            assertEquals(sw.vertical, 373);
            assertEquals(sw.splashIm, splashIm);
        } catch (Exception e) {
        }
    }

    /** Test of paint method. */
    public void testPaint() {
        JFrame f = new JFrame("hi");
        try {
            File greg = new File("./images/paint_splash.jpeg");
            JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(new FileInputStream(greg));
            Image splashIm = decoder.decodeAsBufferedImage();
            BufferedImage buffIm = (BufferedImage) splashIm;
            SplashWindow sw = new SplashWindow(f, splashIm, 473, 373);
            sw.paint(buffIm.createGraphics());
            assertEquals(sw.horizontal, 473);
            assertEquals(sw.vertical, 373);
            assertEquals(sw.splashIm, splashIm);
        } catch (Exception e) {
        }
    }
}
