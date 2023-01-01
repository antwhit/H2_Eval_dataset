import java.awt.BorderLayout;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PantallaLogin extends JDialog {

    private static final long serialVersionUID = 1L;

    private AplicacionComprador ac;

    private AplicacionEmpleado ae;

    private JPanel jContentPane = null;

    private JPanel jPanel = null;

    private JLabel jLabel = null;

    private JLabel jLabel1 = null;

    private JTextField txtCUsuario = null;

    private JTextField txtPassword = null;

    private JButton btnLogin = null;

    private ControladorPantallaLogin cpl = null;

    public PantallaLogin(AplicacionComprador ac) {
        super(ac, "Iniciar Sesi�n ...", true);
        this.ac = ac;
        initializeAc();
    }

    public PantallaLogin(AplicacionEmpleado ae) {
        super(ae, "Iniciar Sesi�n ...", true);
        this.ae = ae;
        initializeAe();
        this.setLocationRelativeTo(null);
    }

    /**
   * This method initializes this
   * 
   * @return void
   */
    private void initializeAc() {
        this.setSize(300, 200);
        this.setContentPane(getJContentPane());
        cpl = new ControladorPantallaLogin(this, ac);
        btnLogin.addActionListener(cpl);
    }

    private void initializeAe() {
        this.setSize(300, 200);
        this.setContentPane(getJContentPane());
        cpl = new ControladorPantallaLogin(this, ae);
        btnLogin.addActionListener(cpl);
    }

    /**
   * This method initializes jContentPane
   * 
   * @return javax.swing.JPanel
   */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(new BorderLayout());
            jContentPane.add(getJPanel(), BorderLayout.CENTER);
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
            jLabel1 = new JLabel();
            jLabel1.setBounds(new Rectangle(18, 63, 72, 16));
            jLabel1.setText("Password:");
            jLabel = new JLabel();
            jLabel.setBounds(new Rectangle(19, 35, 67, 16));
            jLabel.setText("C�digo usuario:");
            jPanel = new JPanel();
            jPanel.setLayout(null);
            jPanel.add(jLabel, null);
            jPanel.add(jLabel1, null);
            jPanel.add(getTxtCUsuario(), null);
            jPanel.add(getTxtPassword(), null);
            jPanel.add(getBtnLogin(), null);
        }
        return jPanel;
    }

    /**
   * This method initializes txtCClient
   * 
   * @return javax.swing.JTextField
   */
    public JTextField getTxtCUsuario() {
        if (txtCUsuario == null) {
            txtCUsuario = new JTextField();
            txtCUsuario.setBounds(new Rectangle(104, 36, 122, 20));
        }
        return txtCUsuario;
    }

    /**
   * This method initializes txtPassword
   * 
   * @return javax.swing.JTextField
   */
    public JTextField getTxtPassword() {
        if (txtPassword == null) {
            txtPassword = new JTextField();
            txtPassword.setBounds(new Rectangle(103, 62, 123, 20));
        }
        return txtPassword;
    }

    /**
   * This method initializes btnLogin
   * 
   * @return javax.swing.JButton
   */
    private JButton getBtnLogin() {
        if (btnLogin == null) {
            btnLogin = new JButton();
            btnLogin.setBounds(new Rectangle(103, 104, 113, 28));
            btnLogin.setText("Iniciar Sesi�n");
        }
        return btnLogin;
    }
}
