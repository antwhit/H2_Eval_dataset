/**
 * Insert the type's description here.
 * Creation date: (17.04.2001 18:59:50)
 * @author:
 */
public class TableModel extends javax.swing.table.AbstractTableModel {

    /**
	 * R�ume
	 */
    protected java.util.Vector data = new java.util.Vector();

    protected java.util.Hashtable ids = new java.util.Hashtable();

    /**
	 * Spaltennamen
	 */
    protected java.lang.String[] rowNames = null;

    /**
	 * TableModel constructor comment.
	 */
    public TableModel() {
        super();
    }

    /**
	 * Insert the method's description here.
	 * Creation date: (24.04.2001 17:27:38)
	 * @param val java.lang.Object
	 */
    public void add(Object val) {
        data.add(val);
        this.fireTableDataChanged();
    }

    public void addUnique(Object val, String id) {
        if (!ids.containsKey(id)) {
            ids.put(id, id);
            data.add(val);
            this.fireTableDataChanged();
        }
    }

    /**
	 * getColumnCount method comment.
	 */
    public int getColumnCount() {
        if (rowNames != null) {
            return rowNames.length;
        } else return 0;
    }

    /**
	 * Insert the method's description here.
	 * Creation date: (17.04.2001 19:07:03)
	 * @return java.lang.String
	 * @param col int
	 */
    public String getColumnName(int col) {
        return rowNames[col];
    }

    /**
	 * Insert the method's description here.
	 * Creation date: (17.04.2001 19:00:46)
	 * @return java.lang.Object[]
	 */
    public java.util.Vector getData() {
        return data;
    }

    /**
	 * getRowCount method comment.
	 */
    public int getRowCount() {
        if (data != null) {
            return data.size();
        } else return 0;
    }

    /**
	 * Insert the method's description here.
	 * Creation date: (17.04.2001 19:01:07)
	 * @return java.lang.String[]
	 */
    public java.lang.String[] getRowNames() {
        return rowNames;
    }

    /**
	 * getValueAt method comment.
	 */
    public Object getValueAt(int arg1, int arg2) {
        if (arg2 == 0) return data.get(arg1);
        if (arg2 == 1) {
            boolean b = ((Room) data.get(arg1)).isActive();
            if (b) return "aktiv"; else return "inaktiv";
        }
        return null;
    }

    /**
	 * Insert the method's description here.
	 * Creation date: (17.04.2001 20:00:31)
	 * @return boolean
	 * @param row int
	 * @param col int
	 */
    public boolean isCellEditable(int row, int col) {
        if (col == 0) return false; else return true;
    }

    /**
	 * Insert the method's description here.
	 * Creation date: (24.04.2001 17:22:53)
	 * @param newData java.util.Vector
	 */
    public void setData(java.util.Vector newData) {
        data = newData;
    }

    /**
	 * Insert the method's description here.
	 * Creation date: (17.04.2001 19:01:07)
	 * @param newRowNames java.lang.String[]
	 */
    public void setRowNames(java.lang.String[] newRowNames) {
        rowNames = newRowNames;
    }

    /**
	 * Insert the method's description here.
	 * Creation date: (17.04.2001 19:08:20)
	 * @param val java.lang.Object
	 * @param row int
	 * @param col int
	 */
    public void setValueAt(Object val, int row, int col) {
        if (col == 0) {
            if (row < data.size()) data.setElementAt(val, row); else if (row == -1) data.add(val); else data.add(val);
        }
        if (col == 1) {
            if (val.equals("aktiv")) ((Room) data.get(row)).setActive(true);
            if (val.equals("inaktiv")) ((Room) data.get(row)).setActive(false);
        }
        this.fireTableDataChanged();
    }

    public void removeValueAt(int row, int col) {
        if (col == 0 && getRowCount() > row) {
            data.remove(row);
            this.fireTableDataChanged();
        }
    }

    /**
 * Insert the method's description here.
 * Creation date: (15.05.2001 16:50:32)
 */
    public void removeAll() {
        if (data != null) data.removeAllElements();
    }

    /**
 * Insert the method's description here.
 * Creation date: (15.05.2001 16:50:32)
 */
    public void removeAllElements() {
        if (data != null) {
            data.removeAllElements();
            fireTableDataChanged();
        }
    }
}
