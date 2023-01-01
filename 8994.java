import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 * Job List Cell renderer. This class gives each job a (partially unique) color.
 * 
 * @version 1.0.0
 * 
 * @author Marijn van Galen <m.p.vangalen@its.tudelft.nl>
 * @author Alexander S. Koning <skoning@ch.tudelft.nl>
 * @author Thomas A. de Ruiter <thomas@de-ruiter.cx>
 * @author Zheng Shen <zhengshen@hotmail.com>
 * @author Berend J. Wouda <bjwouda@hotmail.com>
 */
class JobCellRenderer extends JLabel implements ListCellRenderer {

    /**
     * Colors for job display
     */
    private Color[] colors = { new Color(1.0f, 0.0f, 0.0f), new Color(0.0f, 1.0f, 0.0f), new Color(0.0f, 0.0f, 1.0f), new Color(1.0f, 1.0f, 0.0f), new Color(0.0f, 1.0f, 1.0f), new Color(1.0f, 0.0f, 1.0f), new Color(0.5f, 0.0f, 0.0f), new Color(0.0f, 0.5f, 0.0f), new Color(0.0f, 0.0f, 0.5f), new Color(0.5f, 0.5f, 0.0f), new Color(0.0f, 0.5f, 1.0f), new Color(0.5f, 0.0f, 0.5f) };

    public JobCellRenderer() {
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        setText(value.toString());
        int clr = ((Job) value).getJobId() % 12;
        if (isSelected) setBackground(Color.lightGray); else setBackground(Color.white);
        setForeground(colors[clr]);
        return this;
    }
}
