import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import exc.GMCannotCreateRegistry;
import exc.GMCannotReconfigureNetworkingException;
import exc.GMInvalidHostnameException;
import gui.Gui;
import javax.swing.JFrame;
import connector.*;

public class GoMoku3D extends JFrame {

    private Gui g = null;

    protected static RegistryManager registryManager;

    /**
	 * GoMoku3d constructor
	 * @param title
	 * window title
	 */
    public GoMoku3D(String title) {
        super(title);
        g = new Gui(this, getIP());
        this.setLayout(new BorderLayout());
        this.add(g, BorderLayout.CENTER);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        setLocation((int) (toolkit.getScreenSize().getWidth() / 2 - getWidth() / 2), (int) (toolkit.getScreenSize().getHeight() / 2 - getHeight() / 2));
        JFrame.setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    public static void main(String[] args) {
        final GoMoku3D gmk = new GoMoku3D("GoMoku3D");
        gmk.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent evt) {
                RegistryManager.restoreNetworking();
                gmk.dispose();
            }
        });
        System.out.println(getIP());
        try {
            RegistryManager.createRegistry(getIP());
        } catch (GMInvalidHostnameException e) {
            e.printStackTrace();
            try {
                RegistryManager.reconfigureNetworking(getIP());
                RegistryManager.createRegistry(getIP());
            } catch (GMCannotReconfigureNetworkingException e1) {
                try {
                    RegistryManager.forceRegistryCreation();
                } catch (GMCannotCreateRegistry e2) {
                    e2.printStackTrace();
                }
                e1.printStackTrace();
            } catch (GMCannotCreateRegistry e1) {
                e1.printStackTrace();
            } catch (GMInvalidHostnameException e3) {
                e.printStackTrace();
            }
        } catch (GMCannotCreateRegistry e) {
            e.printStackTrace();
        }
        gmk.setVisible(true);
        gmk.g.setMenuGui();
    }

    /**
	 * Return the IP 
	 * @return
	 * the IP
	 */
    private static String getIP() {
        return IpGet.getAllIPs().get(0).getInet_addr();
    }

    @Override
    public void setDefaultCloseOperation(int operation) {
        super.setDefaultCloseOperation(operation);
    }
}
