import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class CenterPanel extends JPanel {

    private InnerCenterPanel icp;

    public CenterPanel() {
        setBackground(new Color(200, 222, 186));
        icp = new InnerCenterPanel();
        add(icp);
    }
}
