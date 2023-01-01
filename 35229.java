import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class TMTotal extends AbstractTableModel implements TableModel {

    public static String[] nombres = { "Base", "IVA", "Total" };

    private Vector vTotal;

    public TMTotal(Vector lista) {
        vTotal = lista;
    }

    public Vector getVector() {
        return vTotal;
    }

    public void addFila(Vector v) {
        vTotal.add(v);
    }

    public Vector getFila(int i) {
        return (Vector) vTotal.get(i);
    }

    public void borraFila(int i) {
        if (i < vTotal.size()) {
            vTotal.remove(i);
        }
    }

    public int getNumFilas() {
        return vTotal.size();
    }

    public String geColumnName(int col) {
        if (col <= nombres.length) {
            return nombres[col].toString();
        } else {
            return null;
        }
    }

    public int getRowCount() {
        return vTotal.size();
    }

    public int getColumnCount() {
        return nombres.length;
    }

    public Object getValueAt(int arg0, int arg1) {
        Vector vFila;
        if (vTotal != null && arg0 <= vTotal.size()) {
            vFila = (Vector) vTotal.get(arg0);
        } else {
            return null;
        }
        if (vFila != null && arg1 <= vFila.size()) {
            return vFila.get(arg1);
        } else {
            return null;
        }
    }
}
