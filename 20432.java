import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Rectangle;
import java.awt.Dimension;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Point;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PantallaBajaPromocion extends JDialog {

    private static final long serialVersionUID = 1L;

    private JPanel jContentPane = null;

    private JPanel jPanel = null;

    private JLabel jLabelLlistatSandwitx = null;

    private JButton jButtonCancellar = null;

    private JButton jButtonAcceptar = null;

    private JScrollPane jScrollPaneLlista = null;

    private JTable jTableLlista = null;

    public PantallaBajaPromocion(AplicacionEmpleado ae) {
        initialize();
    }

    /**
	 * This method initializes this
	 * 
	 * @return void
	 */
    private void initialize() {
        this.setSize(494, 384);
        this.setContentPane(getJContentPane());
        this.setTitle("Baixa Promoci�");
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
            jContentPane.add(getJPanel(), null);
            jContentPane.add(getJButtonCancellar(), null);
            jContentPane.add(getJButtonAcceptar(), null);
        }
        return jContentPane;
    }

    /**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
    private JPanel getJPanel() {
        if (jPanel == null) {
            jLabelLlistatSandwitx = new JLabel();
            jLabelLlistatSandwitx.setBounds(new Rectangle(7, 12, 219, 16));
            jLabelLlistatSandwitx.setText("Seleccionar Sandvitx amb promoci�:");
            jPanel = new JPanel();
            jPanel.setLayout(null);
            jPanel.setLocation(new Point(20, 20));
            jPanel.setSize(new Dimension(445, 278));
            jPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            jPanel.add(jLabelLlistatSandwitx, null);
            jPanel.add(getJScrollPaneLlista(), null);
        }
        return jPanel;
    }

    /**
	 * This method initializes jButtonCancellar	
	 * 	
	 * @return javax.swing.JButton	
	 */
    private JButton getJButtonCancellar() {
        if (jButtonCancellar == null) {
            jButtonCancellar = new JButton();
            jButtonCancellar.setText("Cancel�lar");
            jButtonCancellar.setSize(new Dimension(103, 28));
            jButtonCancellar.setLocation(new Point(255, 309));
        }
        return jButtonCancellar;
    }

    /**
	 * This method initializes jButtonAcceptar	
	 * 	
	 * @return javax.swing.JButton	
	 */
    private JButton getJButtonAcceptar() {
        if (jButtonAcceptar == null) {
            jButtonAcceptar = new JButton();
            jButtonAcceptar.setText("Borrar");
            jButtonAcceptar.setSize(new Dimension(100, 28));
            jButtonAcceptar.setLocation(new Point(122, 309));
        }
        return jButtonAcceptar;
    }

    /**
	 * This method initializes jScrollPaneLlista	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
    private JScrollPane getJScrollPaneLlista() {
        if (jScrollPaneLlista == null) {
            jScrollPaneLlista = new JScrollPane();
            jScrollPaneLlista.setBounds(new Rectangle(14, 37, 415, 225));
            jScrollPaneLlista.setViewportView(getJTableLlista());
        }
        return jScrollPaneLlista;
    }

    /**
	 * This method initializes jTableLlista	
	 * 	
	 * @return javax.swing.JTable	
	 */
    private JTable getJTableLlista() {
        if (jTableLlista == null) {
            String[] columna = { "IdSandvitx", "Nom", "Punts M�nims", "Regal", "Descompte", "2x1" };
            Object[][] dades = { { "01", "Sandvitx de...", "50", "", "10%", "" }, { "01", "Sandvitx de...", "100", "", "", "V" }, { "02", "Sandvitx de...", "100", "", "15%", "" }, { "03", "Sandvitx de...", "50", "", "5%", "" }, { "04", "Sandvitx de...", "50", "postre", "", "" } };
            jTableLlista = new JTable(dades, columna);
        }
        return jTableLlista;
    }
}
