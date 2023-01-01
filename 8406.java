import com.webobjects.foundation.*;
import com.webobjects.eocontrol.*;
import com.webobjects.eoaccess.*;
import com.webobjects.appserver.*;
import org.apache.log4j.Logger;

public class LoginRequiredDirectAction extends WODirectAction {

    public static final Logger log = Logger.getLogger("er.application.LoginRequiredDirectAction");

    public static String bindingsFromURI(String uri) {
        int index = uri.indexOf("?");
        return index != -1 ? uri.substring(index + 1) : null;
    }

    public LoginRequiredDirectAction(WORequest r) {
        super(r);
    }

    public WOActionResults performActionNamed(String actionName) {
        WOActionResults result;
        log.debug("In performActionNamed: " + actionName);
        if (existingSession() != null) log.debug("Has Session");
        if (((Session) session()).user() != null) {
            log.debug("Has user: " + ((Session) session()).user());
            result = super.performActionNamed(actionName);
        } else {
            log.debug("No user");
            LoginPage p = (LoginPage) pageWithName("LoginPage");
            p.takeValueForKey(bindingsFromURI(request().uri()), "rbindings");
            p.takeValueForKey((actionName == null) ? null : getClass().getName() + "/" + actionName, "raction");
            result = p;
        }
        return result;
    }

    public WOActionResults superSecretAction() {
        return pageWithName("SuperSecretPage");
    }
}
