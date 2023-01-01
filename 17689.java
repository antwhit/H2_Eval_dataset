import java.applet.Applet;
import java.awt.Label;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Manu
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class VerifJava extends Applet {

    /**
     * Affichage de la version
     */
    private Label labVersionVendor;

    /**
     * @see java.applet.Applet#init() 
     */
    public void init() {
        labVersionVendor = new Label(" Version de Java : " + System.getProperty("java.version") + " Editeur : " + System.getProperty("java.vendor"));
        this.add(labVersionVendor);
        String ver = System.getProperty("java.version");
        if (ver.compareTo("1.4") <= 0) {
            stop();
            try {
                String url = getParameter("urlJVMko");
                if (url != null) {
                    getAppletContext().showDocument(new URL("url"), "_blank");
                }
            } catch (MalformedURLException ignored) {
                System.err.println("openDocument::URL invalide");
            }
            return;
        }
        try {
            stop();
            getAppletContext().showDocument(new URL(getDocBase() + "/Main.jsp;jsessionid=" + getParameter("sessionId")), "_self");
        } catch (MalformedURLException e) {
            System.err.println("openDocument::URL invalide");
        }
    }

    /**
     * Retourne le r�pertoire de base de l'aire de jeu
     * @return cha�ne correspondante
     */
    public String getDocBase() {
        String docBase = getDocumentBase().toString();
        if (docBase.indexOf("?") > 0) {
            docBase = docBase.substring(0, docBase.indexOf('?'));
        }
        if (docBase.endsWith(".srv")) {
            docBase = docBase.substring(0, docBase.lastIndexOf('/'));
        }
        return docBase;
    }
}
