import javax.swing.table.AbstractTableModel;
import java.io.File;

/**
 * JDIC API demo class.
 * <p>
 * A redefined TableModel class.
 */
public class MyTableModel extends AbstractTableModel {

    Object columnNamesFile[] = { "Name", "Size", "Type", "Modified" };

    Object columnNamesMyComputer[] = { "Name", "Type", "Size", "FreeSpace" };

    Object columnNames[] = columnNamesFile;

    DiskObject data[] = getTableData();

    public int getRowCount() {
        return (data == null) ? 0 : data.length;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int col) {
        return columnNames[col].toString();
    }

    public Class getColumnClass(int column) {
        return getValueAt(0, column).getClass();
    }

    public Object getValueAt(int row, int col) {
        return data[row];
    }

    public void setValueAt(Object value, int row, int col) {
        throw new UnsupportedOperationException();
    }

    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public void setColumnNames() {
        columnNames = getColumnNames();
    }

    public void setTableData() {
        data = getTableData();
    }

    private Object[] getColumnNames() {
        MyTreeNode selectedTreeNode = FileExplorer.selectedTreeNode;
        if (selectedTreeNode == null) {
            return null;
        }
        File selectedDir = (File) selectedTreeNode.getUserObject();
        if (selectedDir.equals(new File(FileExplorer.MY_COMPUTER_FOLDER_PATH))) {
            return columnNamesMyComputer;
        } else {
            return columnNamesFile;
        }
    }

    private DiskObject[] getTableData() {
        MyTreeNode selectedTreeNode = FileExplorer.selectedTreeNode;
        if (selectedTreeNode == null) {
            return null;
        }
        File selectedDir = (File) selectedTreeNode.getUserObject();
        if (selectedDir.equals(new File(FileExplorer.MY_COMPUTER_FOLDER_PATH))) {
            File[] drivers = MyUtility.getRoots();
            int driverNum = drivers.length;
            int firstDriverNum = 0;
            if (drivers[firstDriverNum].getPath().toLowerCase().startsWith("a:")) {
                firstDriverNum = 1;
            }
            DiskObject[] data = new DiskObject[driverNum - firstDriverNum];
            int curDriverNum = 0;
            for (int i = firstDriverNum; i < driverNum; i++) {
                data[curDriverNum] = new DiskObject(drivers[i], DiskObject.TYPE_DRIVER);
                curDriverNum++;
            }
            return data;
        } else {
            File[] files = selectedDir.listFiles();
            if (files == null) {
                return null;
            }
            int fileNum = files.length;
            DiskObject data[] = new DiskObject[fileNum];
            int curFileNum = 0;
            for (int i = 0; i < fileNum; i++) {
                File file = files[i];
                if (!file.isDirectory()) {
                    data[curFileNum] = new DiskObject(file, DiskObject.TYPE_FILE);
                    curFileNum++;
                }
            }
            for (int i = 0; i < fileNum; i++) {
                File file = files[i];
                if (file.isDirectory()) {
                    data[curFileNum] = new DiskObject(file, DiskObject.TYPE_FOLDER);
                    curFileNum++;
                }
            }
            return data;
        }
    }
}
