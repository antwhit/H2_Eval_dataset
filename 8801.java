import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import net.sourceforge.filebot.ui.transfer.LoadAction;
import net.sourceforge.filebot.ui.transfer.SaveAction;

public class Main {

    /**
	 * @param args
	 */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            Logger.getLogger("global").log(Level.SEVERE, "Failed to set system laf", e);
        }
        final JFrame frame = new JFrame("filebot-dnd-sample");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FileList fileList = new FileList();
        LoadAction loadAction = new LoadAction(fileList.getTransferablePolicy());
        SaveAction saveAction = new SaveAction(fileList.getExportHandler());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(new JButton(loadAction));
        buttonPanel.add(new JButton(saveAction));
        JComponent c = (JComponent) frame.getContentPane();
        c.add(new JScrollPane(fileList), BorderLayout.CENTER);
        c.add(buttonPanel, BorderLayout.SOUTH);
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                frame.setVisible(true);
            }
        });
    }
}
