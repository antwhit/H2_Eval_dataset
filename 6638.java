import fr.esrf.tangoatk.widget.util.jdraw.JDrawEditor;
import fr.esrf.tangoatk.widget.util.jdraw.JDLabel;
import fr.esrf.tangoatk.widget.util.ATKGraphicsUtils;
import javax.swing.*;

public class HelloWorld extends JFrame {

    JDrawEditor theGraph;

    JDLabel label;

    public HelloWorld() {
        theGraph = new JDrawEditor(JDrawEditor.MODE_PLAY);
        label = new JDLabel("myLabel", "Hello World", 5, 5);
        theGraph.addObject(label);
        theGraph.computePreferredSize();
        setContentPane(theGraph);
    }

    public static void main(String[] args) {
        final HelloWorld hw = new HelloWorld();
        ATKGraphicsUtils.centerFrameOnScreen(hw);
        hw.setVisible(true);
    }
}
