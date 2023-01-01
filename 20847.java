import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;

public class PantallaConsultaFamilia extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel jContentPane = null;

    /**
	 * This is the default constructor
	 */
    public PantallaConsultaFamilia() {
        super();
        initialize();
    }

    /**
	 * This method initializes this
	 * 
	 * @return void
	 */
    private void initialize() {
        this.setSize(376, 216);
        this.setContentPane(getJContentPane());
        this.setTitle("Consulta Familia");
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
        }
        return jContentPane;
    }
}
