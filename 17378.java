import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Point;

public class PantallaProductoInexistente extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel jContentPane = null;

    private JLabel jArticleinex = null;

    private JLabel jarticleinex2 = null;

    private JButton jAccep = null;

    /**
	 * This is the default constructor
	 */
    public PantallaProductoInexistente() {
        super();
        initialize();
    }

    /**
	 * This method initializes this
	 * 
	 * @return void
	 */
    private void initialize() {
        this.setSize(409, 200);
        this.setContentPane(getJContentPane());
        this.setTitle("Producto Inexistente");
    }

    /**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jarticleinex2 = new JLabel();
            jarticleinex2.setBounds(new Rectangle(60, 75, 270, 16));
            jarticleinex2.setText("Por favor, verificar el codigo del Producto");
            jArticleinex = new JLabel();
            jArticleinex.setBounds(new Rectangle(59, 45, 132, 16));
            jArticleinex.setText("Producto inexistente");
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(jArticleinex, null);
            jContentPane.add(jarticleinex2, null);
            jContentPane.add(getJAccep(), null);
        }
        return jContentPane;
    }

    /**
	 * This method initializes jAccep	
	 * 	
	 * @return javax.swing.JButton	
	 */
    private JButton getJAccep() {
        if (jAccep == null) {
            jAccep = new JButton();
            jAccep.setText("Aceptar");
            jAccep.setSize(new Dimension(98, 27));
            jAccep.setLocation(new Point(156, 127));
        }
        return jAccep;
    }
}
