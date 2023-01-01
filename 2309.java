import java.awt.Button;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;
import ren.util.PO;

public class TestLookAndFeel {

    public static void main(String[] args) {
        try {
            new TestLookAndFeel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TestLookAndFeel() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            PO.p(info.getName());
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
        JFrame jf = new JFrame();
        JPanel pan = new JPanel();
        JTabbedPane jtp = new JTabbedPane();
        JPanel tp1 = new JPanel();
        JPanel tp2 = new JPanel();
        JButton but1 = new JButton("button!");
        JButton but2 = new JButton("button two!");
        Button awtbut = new Button("awtbut");
        Box b = new Box(1);
        b.add(awtbut);
        tp1.add(but1);
        tp2.add(but2);
        tp2.add(b);
        jtp.addTab("tp1", tp1);
        jtp.addTab("tp2", tp2);
        pan.add(jtp);
        jf.getContentPane().add(pan);
        jf.pack();
        jf.setVisible(true);
    }
}
