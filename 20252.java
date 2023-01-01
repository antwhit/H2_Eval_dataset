import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import jxl.*;
import jxl.write.*;

public class XlsReader {

    private ReportTable _rt;

    public XlsReader() {
    }

    ;

    public ReportTable read(File file) {
        if (loadXlsFile(file) == true) return _rt; else {
            JOptionPane.showMessageDialog(null, "XLS File can not be loaded");
            return null;
        }
    }

    public void write(ReportTable rt, File file) {
        saveXlsFile(rt, file);
    }

    private boolean loadXlsFile(File file) {
        try {
            int index = 0;
            Workbook workbook = Workbook.getWorkbook(file);
            Sheet sheet = workbook.getSheet(0);
            int col = sheet.getColumns();
            int row = sheet.getRows();
            int sel = JOptionPane.showConfirmDialog(null, "Is First Row  Column Header ?");
            if (sel == JOptionPane.CANCEL_OPTION) return false;
            String[] colName = new String[col];
            if (sel == JOptionPane.NO_OPTION) {
                for (int i = 0; i < colName.length; i++) colName[i] = "Column_" + (i + 1);
            }
            if (sel == JOptionPane.YES_OPTION) {
                for (int i = 0; i < colName.length; i++) {
                    Cell a1 = sheet.getCell(i, 0);
                    if (a1 == null || a1.getType() == CellType.EMPTY || a1.getType() == CellType.ERROR) colName[i] = "Column_" + (i + 1); else colName[i] = a1.getContents();
                }
                index = 1;
            }
            _rt = new ReportTable(colName, true, true);
            _rt.addRows(0, row - index);
            for (int i = index; i < row; i++) for (int j = 0; j < col; j++) {
                Cell a1 = sheet.getCell(j, i);
                if (a1 == null || a1.getType() == CellType.EMPTY || a1.getType() == CellType.ERROR) continue;
                _rt.setValueAt(a1.getContents(), i - index, j);
            }
            workbook.close();
        } catch (JXLException e) {
            ConsoleFrame.addText("\n Exception:" + e.getMessage());
            return false;
        } catch (IOException e) {
            ConsoleFrame.addText("\n Exception:" + e.getMessage());
            return false;
        }
        return true;
    }

    private boolean saveXlsFile(ReportTable rt, File file) {
        try {
            WritableWorkbook workbook = Workbook.createWorkbook(file);
            WritableSheet sheet = workbook.createSheet("Arrah Sheet", 0);
            int row = rt.table.getRowCount();
            int col = rt.table.getColumnCount();
            for (int j = 0; j < col; j++) {
                Label label = new Label(j, 0, rt.table.getColumnName(j));
                sheet.addCell(label);
            }
            for (int i = 0; i < row; i++) for (int j = 0; j < col; j++) {
                Object obj = rt.table.getValueAt(i, j);
                if (obj == null) continue;
                Label label = new Label(j, i + 1, obj.toString());
                sheet.addCell(label);
            }
            workbook.write();
            workbook.close();
        } catch (Exception e) {
            ConsoleFrame.addText("\n Save Exception:" + e.getMessage());
            return false;
        }
        return true;
    }
}
