import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jmol.adapter.cdk.CdkJmolAdapter;
import org.jmol.api.JmolAdapter;
import org.jmol.api.JmolViewer;

/**
 * A example of integrating the Jmol viewer into a CDK application.
 *
 * <p>I compiled/ran this code directly in the examples directory by doing:
 * <pre>
 * javac -classpath ../Jmol.jar CDKIntegration.java
 * java -cp .:../Jmol.jar:../../../CDK/cdk/dist/jar/cdk-all.jar CDKIntegration
 * </pre>
 *
 * @author Miguel <mth@mth.com>
 * @author Egon <egonw@jmol.org>
 */
public class CDKIntegrationPDB {

    public static void main(String[] argv) {
        if (argv.length != 1) {
            System.out.println("Syntax: CDKIntegrationPDB <filename>");
            System.exit(-1);
        }
        String filename = argv[0];
        JFrame frame = new JFrame("CDK Integration Example");
        frame.addWindowListener(new ApplicationCloser());
        Container contentPane = frame.getContentPane();
        JmolPanel jmolPanel = new JmolPanel();
        contentPane.add(jmolPanel);
        frame.setSize(300, 300);
        frame.setVisible(true);
        JmolViewer viewer = jmolPanel.getViewer();
        viewer.openFile(filename);
        String strError = viewer.getOpenFileError();
        if (strError != null) System.out.println(strError);
        viewer.evalString(strScript);
    }

    static final String strScript = "select *; spacefill off; cartoons on; color cartoons structure;";

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
