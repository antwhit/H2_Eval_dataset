import javax.swing.table.*;
import java.util.Vector;

class GroupTableModel extends AbstractTableModel {

    private static final Integer zero = new Integer(0);

    private Vector dispList, colNames;

    public int getRowCount() {
        return dispList.size();
    }

    public int getColumnCount() {
        return colNames.size();
    }

    public String getColumnName(int i) {
        return (String) colNames.elementAt(i);
    }

    public Class getColumnClass(int i) {
        switch(i) {
            case 0:
                return Boolean.class;
            case 1:
                return String.class;
            case 2:
            case 3:
                return Integer.class;
            default:
                return Object.class;
        }
    }

    public void setValueAt(Object o, int i, int j) {
        switch(j) {
            case 0:
                ((NNTPGroup) dispList.elementAt(i)).subscribe(((Boolean) o).booleanValue());
                break;
            case 1:
                ((NNTPGroup) dispList.elementAt(i)).setName((String) o);
                break;
            case 2:
                ((NNTPGroup) dispList.elementAt(i)).setLocalCount(((Integer) o).intValue());
                break;
            case 3:
                ((NNTPGroup) dispList.elementAt(i)).setServerCount(((Integer) o).intValue());
                break;
        }
    }

    public Object getValueAt(int i, int j) {
        NNTPGroup x = (NNTPGroup) dispList.elementAt(i);
        switch(j) {
            case 0:
                return new Boolean(x.getSubscribed());
            case 1:
                return x.getName();
            case 2:
                return new Integer(x.getLocalCount());
            case 3:
                return new Integer(x.getServerCount());
            default:
                return (Object) x;
        }
    }

    public GroupTableModel(Vector myList, Vector colList) {
        super();
        dispList = myList;
        colNames = colList;
    }
}
