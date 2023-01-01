import java.text.*;
import java.util.*;
import javax.swing.table.*;

class DataTableModel extends AbstractTableModel {

    private SimpleFrame mainFrame;

    private DataSink mySink;

    public DataTableModel(SimpleFrame frame, DataSink aSink) {
        this.mainFrame = frame;
        mySink = aSink;
    }

    public int getRowCount() {
        if (this.mySink.getColumnCount() == 0) return 0;
        int nRet = mySink.getdataLines().size();
        return nRet;
    }

    public int getColumnCount() {
        return this.mySink.getColumnCount() + 1;
    }

    public Object getValueAt(int r, int c) {
        String sNull = "";
        NumberFormat formatter = NumberFormat.getIntegerInstance();
        int nCount = this.mySink.getColumnCount();
        if ((nCount + 1) > c && nCount > 0) {
            ArrayList myList = this.mySink.getdataLines();
            if (myList.size() > r) {
                if (c > 0) {
                    ArrayList cell = (ArrayList) ((Object[]) myList.get(r))[c - 1];
                    String strCell = "";
                    String strPart = "";
                    String strBit;
                    int i = 0;
                    while (i < cell.size()) {
                        strBit = (String) cell.get(i);
                        if (strBit.length() > 0) {
                            strCell += strPart + strBit;
                            strPart = "\n";
                        }
                        i++;
                    }
                    return (Object) strCell;
                } else {
                    return (Object) formatter.format(r + 1);
                }
            }
        }
        return sNull;
    }

    public String getColumnName(int c) {
        if (this.mySink.getColumnCount() == 0) return "";
        if (c > 0) return this.mySink.getColumnName(c - 1); else return "Row";
    }

    public int getRowLineCount(int nRow) {
        return this.mySink.getRowLineCount(nRow);
    }
}
