import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.vecmath.Point3d;
import org.jmol.adapter.cdk.CdkJmolAdapter;
import org.jmol.api.JmolAdapter;
import org.jmol.api.JmolViewer;
import org.openscience.cdk.Atom;
import org.openscience.cdk.Molecule;

/**
 * A example of integrating the Jmol viewer into a CDK application.
 *
 * <p>I compiled/ran this code directly in the examples directory by doing:
 * <pre>
 * javac -classpath ../Jmol.jar:../../../CDK/cdk/dist/jar/cdk-all.jar CDKIntegration2.java
 * java -cp .:../Jmol.jar:../../../CDK/cdk/dist/jar/cdk-all.jar CDKIntegration2
 * </pre>
 *
 * @author Miguel <mth@mth.com>
 * @author Egon <egonw@jmol.org>
 */
public class CDKIntegration2 {

    public static void main(String[] argv) {
        JFrame frame = new JFrame("CDK Integration Example");
        frame.addWindowListener(new ApplicationCloser());
        Container contentPane = frame.getContentPane();
        JmolPanel jmolPanel = new JmolPanel();
        contentPane.add(jmolPanel);
        frame.setSize(300, 300);
        frame.setVisible(true);
        JmolViewer viewer = jmolPanel.getViewer();
        Molecule methane = new Molecule();
        Atom atom = new Atom("C");
        atom.setPoint3d(new Point3d(0.257, -0.363, 0.000));
        methane.addAtom(atom);
        atom = new Atom("H");
        atom.setPoint3d(new Point3d(0.257, 0.727, 0.000));
        methane.addAtom(atom);
        atom = new Atom("H");
        atom.setPoint3d(new Point3d(0.771, -0.727, 0.890));
        methane.addAtom(atom);
        atom = new Atom("H");
        atom.setPoint3d(new Point3d(0.771, -0.727, -0.890));
        methane.addAtom(atom);
        atom = new Atom("H");
        atom.setPoint3d(new Point3d(-0.771, -0.727, 0.000));
        methane.addAtom(atom);
        viewer.openClientFile("", "", methane);
        viewer.evalString(strScript);
        String strError = viewer.getOpenFileError();
        if (strError != null) System.out.println(strError);
    }

    static final String strScript = "select *; spacefill on;";

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
