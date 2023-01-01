import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public abstract class StatisticsTable extends JPanel implements ComponentListener {

    public static final String[] columnNames = { "Number", "Language1", "Language2", "Level", "Correct", "Total" };

    private static final long serialVersionUID = 12L;

    protected JTable table;

    protected StatisticsTableModel tableModel;

    public StatisticsTable() {
        JScrollPane scroller;
        table = new JTable();
        table.setSurrendersFocusOnKeystroke(true);
        scroller = new javax.swing.JScrollPane(table);
        table.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 300));
        setLayout(new BorderLayout());
        add(scroller, BorderLayout.CENTER);
        addComponentListener(this);
    }

    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
    }

    public void componentShown(ComponentEvent e) {
        update();
    }

    public void update() {
        Dictionary dict = Dictionary.getDictionary();
        columnNames[1] = dict.getSrc();
        columnNames[2] = dict.getDest();
        tableModel.update();
        tableModel.updateColumnNames(columnNames);
        tableModel.fireTableRowsInserted(0, dict.getSize());
    }
}
