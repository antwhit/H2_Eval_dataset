import java.net.*;
import java.util.Vector;
import org.gjt.sp.jedit.*;
import org.gjt.sp.jedit.gui.OptionsDialog;
import org.gjt.sp.jedit.msg.PropertiesChanged;
import org.gjt.sp.util.Log;

/**
 * FirewallPlugin - a firewall authenticator plugin for jEdit
 * @author Dirk Moebius <dmoebius@gmx.net>
 * @version 0.2.1
 */
public class FirewallPlugin extends EBPlugin {

    public void start() {
        propertiesChanged();
    }

    public void createOptionPanes(OptionsDialog optionsDialog) {
        optionsDialog.addOptionPane(new FirewallOptionPane());
    }

    public void handleMessage(EBMessage msg) {
        if (msg instanceof PropertiesChanged) {
            propertiesChanged();
        }
    }

    private void propertiesChanged() {
        boolean enabled = jEdit.getBooleanProperty("firewall.enabled");
        if (!enabled) {
            Log.log(Log.DEBUG, this, "Firewall disabled");
            System.getProperties().remove("proxySet");
            System.getProperties().remove("proxyHost");
            System.getProperties().remove("proxyPort");
            System.getProperties().remove("http.proxyHost");
            System.getProperties().remove("http.proxyPort");
            System.getProperties().remove("http.nonProxyHosts");
            Authenticator.setDefault(null);
        } else {
            String host = jEdit.getProperty("firewall.host");
            if (host == null) {
                return;
            }
            System.setProperty("http.proxyHost", host);
            Log.log(Log.DEBUG, this, "Firewall enabled: " + host);
            String port = jEdit.getProperty("firewall.port");
            if (port != null) {
                System.setProperty("http.proxyPort", port);
            }
            String nonProxyHosts = jEdit.getProperty("firewall.nonProxyHosts");
            if (nonProxyHosts != null) {
                System.setProperty("http.nonProxyHosts", nonProxyHosts);
            }
            String username = jEdit.getProperty("firewall.user");
            if (username == null || username.length() == 0) {
                Log.log(Log.DEBUG, this, "Firewall without user");
                Authenticator.setDefault(new FirewallAuthenticator(null));
            } else {
                Log.log(Log.DEBUG, this, "Firewall user: " + username);
                PasswordAuthentication pw = new PasswordAuthentication(username, jEdit.getProperty("firewall.password").toCharArray());
                Authenticator.setDefault(new FirewallAuthenticator(pw));
            }
        }
    }
}

class FirewallAuthenticator extends Authenticator {

    PasswordAuthentication pw = null;

    public FirewallAuthenticator(PasswordAuthentication pw) {
        this.pw = pw;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return pw;
    }
}
