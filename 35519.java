import com.webobjects.foundation.*;
import com.webobjects.appserver.*;
import com.webobjects.eocontrol.*;
import com.webobjects.eoaccess.*;
import com.webobjects.directtoweb.*;
import er.extensions.ERXExtensions;
import er.wrox.User;

public class LinkToEditUser extends WOComponent {

    public LinkToEditUser(WOContext context) {
        super(context);
    }

    public boolean isStateless() {
        return true;
    }

    public EOEnterpriseObject object() {
        return (EOEnterpriseObject) valueForBinding("object");
    }

    public User user() {
        return (User) object();
    }

    public WOComponent editUser() {
        EOEditingContext peer = ERXExtensions.newEditingContext();
        EditPageInterface epi = (EditPageInterface) D2W.factory().pageForConfigurationNamed("EditUser", session());
        epi.setObject(EOUtilities.localInstanceOfObject(peer, user()));
        epi.setNextPage(context().page());
        return (WOComponent) epi;
    }
}
