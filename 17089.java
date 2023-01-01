import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JDialog;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;

public class PantallaListadoFactura extends JDialog {

    private static final long serialVersionUID = 1L;

    private JPanel jContentPane = null;

    private JLabel jLlistat = null;

    private JRadioButton jTotalstock = null;

    private JRadioButton jTotalstock1 = null;

    private JLabel jTotalStock1 = null;

    private JLabel jStockmp1 = null;

    private JTextField jTextField = null;

    private JButton jAcceptar = null;

    private JButton jCancelar = null;

    private ControladorPantallaListarFacturas cplf = null;

    private AplicacionEmpleado ae;

    /**
	 * This is the default constructor
	 */
    public PantallaListadoFactura(AplicacionEmpleado ae) {
        super(ae, "Listado Facturas", true);
        this.ae = ae;
        initialize();
    }

    /**
	 * This method initializes this
	 * 
	 * @return void
	 */
    private void initialize() {
        this.setSize(518, 293);
        this.setContentPane(getJContentPane());
        this.setTitle("Listado Factura");
        cplf = new ControladorPantallaListarFacturas(this, ae);
        jTotalstock.setActionCommand("A");
        jTotalstock.addActionListener(cplf);
        jTotalstock1.setActionCommand("B");
        jTotalstock1.addActionListener(cplf);
    }

    /**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jStockmp1 = new JLabel();
            jStockmp1.setBounds(new Rectangle(181, 140, 146, 25));
            jStockmp1.setText("Factura por Producto:");
            jTotalStock1 = new JLabel();
            jTotalStock1.setBounds(new Rectangle(181, 99, 116, 24));
            jTotalStock1.setText("Facturacion Total");
            jLlistat = new JLabel();
            jLlistat.setBounds(new Rectangle(141, 51, 36, 16));
            jLlistat.setText("Listar:");
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(jLlistat, null);
            jContentPane.add(getJTotalstock(), null);
            jContentPane.add(getJTotalstock1(), null);
            jContentPane.add(jTotalStock1, null);
            jContentPane.add(jStockmp1, null);
            jContentPane.add(getJTextField(), null);
            jContentPane.add(getJAcceptar(), null);
            jContentPane.add(getJCancelar(), null);
        }
        return jContentPane;
    }

    /**
	 * This method initializes jTotalstock	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
    private JRadioButton getJTotalstock() {
        if (jTotalstock == null) {
            jTotalstock = new JRadioButton();
            jTotalstock.setBounds(new Rectangle(143, 99, 21, 21));
        }
        return jTotalstock;
    }

    /**
	 * This method initializes jTotalstock1	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
    private JRadioButton getJTotalstock1() {
        if (jTotalstock1 == null) {
            jTotalstock1 = new JRadioButton();
            jTotalstock1.setBounds(new Rectangle(142, 144, 21, 21));
        }
        return jTotalstock1;
    }

    /**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
    private JTextField getJTextField() {
        if (jTextField == null) {
            jTextField = new JTextField();
            jTextField.setBounds(new Rectangle(343, 142, 153, 19));
        }
        return jTextField;
    }

    /**
	 * This method initializes jAcceptar	
	 * 	
	 * @return javax.swing.JButton	
	 */
    private JButton getJAcceptar() {
        if (jAcceptar == null) {
            jAcceptar = new JButton();
            jAcceptar.setBounds(new Rectangle(147, 192, 79, 26));
            jAcceptar.setText("Aceptar");
        }
        return jAcceptar;
    }

    /**
	 * This method initializes jCancelar	
	 * 	
	 * @return javax.swing.JButton	
	 */
    private JButton getJCancelar() {
        if (jCancelar == null) {
            jCancelar = new JButton();
            jCancelar.setBounds(new Rectangle(301, 191, 85, 26));
            jCancelar.setText("Cancelar");
        }
        return jCancelar;
    }
}
