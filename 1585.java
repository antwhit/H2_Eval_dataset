import javax.swing.DefaultListModel;

public class DefaultMutableListModel extends DefaultListModel implements MutableListModel {

    public boolean isCellEditable(int index) {
        return true;
    }

    public void setValueAt(Object value, int index) {
        super.setElementAt(value, index);
    }
}
