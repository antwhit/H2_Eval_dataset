import javax.swing.table.AbstractTableModel;
import java.util.*;

public class EditCardsTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    GUIcardsEdit editGui;

    private ArrayList<Card> editList;

    private String[] columnNames;

    private Object[][] data;

    private Object[][] oldData;

    private int maxNumTags;

    EditCardsTableModel(GUIcardsEdit editGui, ArrayList<Card> editList, Object[][] oldData) {
        maxNumTags = Card.maxNumTags;
        {
            columnNames = new String[maxNumTags + 3];
            columnNames[0] = "";
            columnNames[1] = "Front";
            columnNames[2] = "Back";
            for (int i = 0; i < maxNumTags; i++) {
                int col = i + 1;
                columnNames[i + 3] = "Tag" + col;
            }
        }
        this.editGui = editGui;
        this.oldData = oldData;
        this.editList = editList;
        data = new Object[editList.size()][maxNumTags + 3];
        populateEditCardsTable();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    public boolean isCellEditable(int row, int col) {
        if (col == 0) return false;
        return true;
    }

    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }

    public Object[][] getOldData() {
        return data;
    }

    public void populateEditCardsTable() {
        if (true) {
            for (int i = 0; i < editList.size(); i++) {
                data[i][0] = i + 1;
                Card card = editList.get(i);
                data[i][1] = card.getFront().toString();
                data[i][2] = card.getBack().toString();
                ArrayList<Tag> tags = card.getTags();
                for (int j = 0; j < tags.size(); j++) {
                    data[i][j + 3] = tags.get(j).toString();
                }
            }
        } else {
            data = oldData;
        }
    }
}
