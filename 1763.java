import com.webobjects.foundation.*;
import com.webobjects.appserver.*;
import com.webobjects.eocontrol.*;
import com.webobjects.eoaccess.*;
import com.webobjects.directtoweb.*;
import er.extensions.ERXUtilities;
import er.extensions.ERXExtensions;
import org.apache.log4j.Logger;

public class Home extends WOComponent {

    public Home(WOContext context) {
        super(context);
    }

    public WOComponent editMyInformation() {
        EOEditingContext peer = ERXExtensions.newEditingContext();
        EditPageInterface epi = (EditPageInterface) D2W.factory().pageForConfigurationNamed("EditUser", session());
        epi.setObject(ERXUtilities.localInstanceOfObject(peer, ((Session) session()).user()));
        epi.setNextPage(context().page());
        return (WOComponent) epi;
    }

    public WOComponent createUser() {
        EOEditingContext peer = ERXExtensions.newEditingContext();
        EditPageInterface epi = (EditPageInterface) D2W.factory().pageForConfigurationNamed("EditUser", session());
        epi.setObject(ERXUtilities.createEO("User", peer));
        epi.setNextPage(context().page());
        return (WOComponent) epi;
    }

    public WOComponent createGroup() {
        EOEditingContext peer = ERXExtensions.newEditingContext();
        EditPageInterface epi = (EditPageInterface) D2W.factory().pageForConfigurationNamed("EditGroup", session());
        epi.setObject(ERXUtilities.createEO("Group", peer));
        epi.setNextPage(context().page());
        return (WOComponent) epi;
    }

    public WOComponent listUsers() {
        ListPageInterface lpi = (ListPageInterface) D2W.factory().pageForConfigurationNamed("ListAllUsers", session());
        lpi.setDataSource(((Session) session()).usersDataSource());
        return (WOComponent) lpi;
    }

    public WOComponent listGroups() {
        ListPageInterface lpi = (ListPageInterface) D2W.factory().pageForConfigurationNamed("ListAllGroups", session());
        lpi.setDataSource(((Session) session()).groupsDataSource());
        return (WOComponent) lpi;
    }

    public WOComponent throwNiceRuntimeException() {
        ((Session) session()).user().valueForKey("foo").toString();
        return null;
    }

    public WOComponent log4jExample() {
        return null;
    }
}
