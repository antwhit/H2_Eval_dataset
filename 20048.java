import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JTable;
import java.awt.Rectangle;

public class PantallaListados extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel jContentPane = null;

    private JTable jTable = null;

    /**
	 * This is the default constructor
	 */
    public PantallaListados() {
        super();
        initialize();
    }

    /**
	 * This method initializes this
	 * 
	 * @return void
	 */
    private void initialize() {
        this.setSize(492, 337);
        this.setContentPane(getJContentPane());
        this.setTitle("Listado");
    }

    /**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(getJTable(), null);
        }
        return jContentPane;
    }

    /**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
    public JTable getJTable() {
        if (jTable == null) {
            jTable = new JTable();
            jTable.setBounds(new Rectangle(47, 27, 379, 230));
        }
        return jTable;
    }

    public void setJContentPane(JPanel contentPane) {
        jContentPane = contentPane;
    }

    public void setJTable(JTable table) {
        jTable = table;
    }
}
