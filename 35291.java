import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * JDIC API demo class.
 * <p>
 * A redefined TableCellRenderer class.
 */
public class MyTableRenderer extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value == null) return null;
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        switch(column) {
            case 0:
                ((DiskObject) value).renderIcon((JLabel) component);
                break;
            case 1:
                ((DiskObject) value).renderSize((JLabel) component);
                break;
            case 2:
                ((DiskObject) value).renderType((JLabel) component);
                break;
            case 3:
                ((DiskObject) value).renderModified((JLabel) component);
                break;
        }
        return component;
    }
}
