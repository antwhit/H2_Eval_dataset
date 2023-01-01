import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * @author trunghpb
 *
 */
public class LookAndFeelPanel extends JPanel {

    public LookAndFeelPanel() {
        UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo info : infos) {
            this.makeButton(info.getName(), info.getClassName());
        }
    }

    void makeButton(String buttonName, final String themeName) {
        JButton button = new JButton(buttonName);
        this.add(button);
        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                try {
                    UIManager.setLookAndFeel(themeName);
                    SwingUtilities.updateComponentTreeUI(LookAndFeelPanel.this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
