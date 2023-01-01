import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Dark_Wolf
 */
public class IconTest {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton redownloadButton = new JButton(new CloseIcon(14, 14));
        redownloadButton.setBackground(new Color(0, 0, 0, 0));
        redownloadButton.setFocusable(false);
        redownloadButton.setOpaque(false);
        redownloadButton.setForeground(Color.WHITE);
        frame.add(redownloadButton);
        frame.pack();
        frame.setVisible(true);
    }
}
