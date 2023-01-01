import java.awt.event.MouseEvent;
import javax.swing.*;

public class JNewsTableMouseAdapter extends JNewsMouseAdapter {

    public JNewsTableMouseAdapter(JPopupMenu showPopup) {
        super(showPopup);
    }

    public JNewsTableMouseAdapter() {
        super();
    }

    protected void internalDoubleClick(MouseEvent e) {
        Object x;
        int index;
        JTable internalTable;
        internalTable = (JTable) e.getComponent();
        index = internalTable.rowAtPoint(e.getPoint());
        x = internalTable.getValueAt(index, -1);
        if (JNConfig.debugging) {
            System.out.println("Double clicked on [" + internalTable.getValueAt(index, 1) + "]");
        }
        OnDoubleClick(internalTable, x, e);
    }
}
