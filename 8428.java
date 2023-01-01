import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Created by IntelliJ IDEA.
 * User: vince
 * Date: 5/14/11
 * Time: 9:39 PM
 */
public class EditQueue {

    private JButton closeButton;

    private JButton removeButton;

    private JTable queueTable;

    public JPanel mainPanel;

    private JButton moveUpButton;

    private JButton moveDownButton;

    private JFrame frame;

    private DefaultTableModel defaultTableModel;

    public EditQueue(JFrame jframe) {
        this.frame = jframe;
        closeButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionEvent) {
                frame.setVisible(false);
            }
        });
        removeButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionEvent) {
                int selectedRow = queueTable.getSelectedRow();
                if (selectedRow >= 0 && selectedRow < XDCCConnectionManager.queue.getRequestQueue().size()) {
                    XDCCConnectionManager.queue.getRequestQueue().remove(XDCCConnectionManager.queue.getRequestQueue().toArray()[selectedRow]);
                    defaultTableModel.removeRow(selectedRow);
                }
            }
        });
        moveUpButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionEvent) {
                int[] selectedRows = queueTable.getSelectedRows();
                Arrays.sort(selectedRows);
                for (int selectedRow : selectedRows) {
                    if (selectedRow != 0) {
                        LinkedList<PackQueueRequest> requestQueue = (LinkedList<PackQueueRequest>) XDCCConnectionManager.queue.getRequestQueue();
                        PackQueueRequest removedItem = requestQueue.remove(selectedRow);
                        requestQueue.add(selectedRow - 1, removedItem);
                    }
                }
                redrawTable();
            }
        });
        moveDownButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionEvent) {
                int[] selectedRows = queueTable.getSelectedRows();
                Arrays.sort(selectedRows);
                for (int selectedRow : selectedRows) {
                    if (selectedRow < queueTable.getRowCount() - 1) {
                        LinkedList<PackQueueRequest> requestQueue = (LinkedList<PackQueueRequest>) XDCCConnectionManager.queue.getRequestQueue();
                        PackQueueRequest removedItem = requestQueue.remove(selectedRow);
                        requestQueue.add(selectedRow + 1, removedItem);
                    }
                }
                redrawTable();
            }
        });
    }

    private void redrawTable() {
        defaultTableModel.setDataVector(buildQueueTableData(), new String[] { "Bot Nick", "Pack #", "Save To" });
    }

    private void createUIComponents() {
        defaultTableModel = new DefaultTableModel(buildQueueTableData(), new String[] { "Bot Nick", "Pack #", "Save To" });
        queueTable = new JTable(defaultTableModel);
    }

    private String[][] buildQueueTableData() {
        if (XDCCConnectionManager.queue == null) {
            return new String[][] {};
        }
        String[][] rows = new String[XDCCConnectionManager.queue.getRequestQueue().size()][];
        int i = 0;
        for (PackQueueRequest packQueueRequest : XDCCConnectionManager.queue.getRequestQueue()) {
            rows[i++] = new String[] { packQueueRequest.getXdccBoxNick(), packQueueRequest.getPackNumber(), packQueueRequest.getSaveTo() };
        }
        return rows;
    }
}
