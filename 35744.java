import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.util.List;

public class TestPanel extends JPanel implements ActionListener {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private JTextField txtAddress;

    private JTextField txtPort;

    private JTextField txtResult;

    JButton btnClose = null;

    private int currentRow = 4;

    TestPanel(List<Test> tests) {
        setLayout(new FormLayout(new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC }, buildRowSpec(tests.size())));
        JLabel lblNewLabel_1 = new JLabel("Connection test");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(lblNewLabel_1, "4, 2, 3, 1, center, default");
        JLabel lblUdpPingDest = new JLabel("Test");
        add(lblUdpPingDest, "2, 4, center, default");
        JLabel lblNewLabel = new JLabel("Address");
        add(lblNewLabel, "4, 4, center, default");
        JLabel lblPort = new JLabel("Port");
        add(lblPort, "6, 4, center, default");
        JLabel lblResult = new JLabel("Result");
        add(lblResult, "8, 4, center, default");
        for (Test test : tests) {
            addTestRow(test, nextRow());
        }
        btnClose = new JButton("Close");
        add(btnClose, "6, " + nextRow() + ", left, default");
        btnClose.addActionListener(this);
    }

    RowSpec[] buildRowSpec(int numberOfTests) {
        RowSpec rs[] = new RowSpec[4 + numberOfTests * 2 + 4];
        for (int i = 0; i < rs.length; ) {
            rs[i++] = FormFactory.RELATED_GAP_ROWSPEC;
            rs[i++] = FormFactory.DEFAULT_ROWSPEC;
        }
        return rs;
    }

    int nextRow() {
        return (currentRow += 2);
    }

    public void addTestRow(int rowNo) {
        JLabel lblTest = new JLabel("Ping to address:");
        add(lblTest, "2, " + rowNo + ", right, default");
        txtAddress = new JTextField();
        txtAddress.setEditable(false);
        txtAddress.setText("0.se.pool.ntp.org");
        add(txtAddress, "4, " + rowNo + ", fill, default");
        txtAddress.setColumns(10);
        txtPort = new JTextField();
        txtPort.setText("123");
        txtPort.setHorizontalAlignment(JTextField.CENTER);
        txtPort.setEditable(false);
        add(txtPort, "6, " + rowNo + ", fill, default");
        txtPort.setColumns(10);
        txtResult = new JTextField();
        txtResult.setEditable(false);
        add(txtResult, "8, " + rowNo + ", fill, default");
        txtResult.setColumns(10);
    }

    public void addTestRow(Test test, int rowNo) {
        add(test.lTest, "2, " + rowNo + ", left, default");
        add(test.txtAddress, "4, " + rowNo + ", fill, default");
        add(test.txtPort, "6, " + rowNo + ", fill, default");
        add(test.txtResult, "8, " + rowNo + ", fill, default");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action == "Close") {
            Test.closeAll();
            System.exit(0);
        }
    }
}
