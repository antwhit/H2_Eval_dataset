import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import org.jmol.adapter.cdk.CdkJmolAdapter;
import org.jmol.api.JmolAdapter;
import org.jmol.api.JmolViewer;
import org.openscience.cdk.Atom;
import org.openscience.cdk.Crystal;

/**
 * Another example of integrating the Jmol viewer into a CDK application.
 *
 * <p>I compiled/ran this code directly in the examples directory by doing:
 * <pre>
 * javac -classpath ../Jmol.jar:../../../CDK/cdk/dist/jar/cdk-all.jar CDKIntegration3.java
 * java -cp .:../Jmol.jar:../../../CDK/cdk/dist/jar/cdk-all.jar CDKIntegration3
 * </pre>
 *
 * @author Miguel <mth@mth.com>
 * @author Egon <egonw@jmol.org>
 */
public class CDKIntegration3 {

    public static void main(String[] argv) {
        JFrame frame = new JFrame("CDK Integration Example");
        frame.addWindowListener(new ApplicationCloser());
        Container contentPane = frame.getContentPane();
        JmolPanel jmolPanel = new JmolPanel();
        contentPane.add(jmolPanel);
        frame.setSize(300, 300);
        frame.setVisible(true);
        JmolViewer viewer = jmolPanel.getViewer();
        Crystal crystal = new Crystal();
        crystal.setID("CesiumChloride");
        crystal.setA(new Vector3d(4.11, 0, 0));
        crystal.setB(new Vector3d(0, 4.11, 0));
        crystal.setC(new Vector3d(0, 0, 4.11));
        Atom cesium = new Atom("Cs");
        cesium.setPoint3d(new Point3d(0.000, 0.000, 0.000));
        crystal.addAtom(cesium);
        Atom chloride = new Atom("Cl");
        chloride.setPoint3d(new Point3d(2.055, 2.055, 2.055));
        crystal.addAtom(chloride);
        viewer.openClientFile("", "", crystal);
        viewer.evalString(strScript);
        String strError = viewer.getOpenFileError();
        if (strError != null) System.out.println(strError);
    }

    static final String strScript = "set unitcell on; select *; spacefill on;";

    static class ApplicationCloser extends WindowAdapter {

        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }

    static class JmolPanel extends JPanel {

        JmolViewer viewer;

        JmolAdapter adapter;

        JmolPanel() {
            adapter = new CdkJmolAdapter(null);
            viewer = JmolViewer.allocateViewer(this, adapter);
        }

        public JmolViewer getViewer() {
            return viewer;
        }

        final Dimension currentSize = new Dimension();

        final Rectangle rectClip = new Rectangle();

        public void paint(Graphics g) {
            viewer.setScreenDimension(getSize(currentSize));
            g.getClipBounds(rectClip);
            viewer.renderScreenImage(g, currentSize, rectClip);
        }
    }
}
