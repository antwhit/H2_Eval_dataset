import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class ColorPanel extends JPanel {

    private Color[] colors = { Color.WHITE, Color.BLACK, Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW };

    public ColorPanel(Whiteboard wb) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        for (int i = 0; i < colors.length; i++) {
            JButton l = new JButton("x");
            l.setPreferredSize(new Dimension(50, 50));
            l.setOpaque(true);
            l.setBackground(colors[i]);
            l.setBorder(new LineBorder(Color.CYAN, 5));
            add(l);
        }
    }
}
