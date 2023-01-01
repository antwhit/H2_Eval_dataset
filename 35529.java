import com.webobjects.foundation.*;
import com.webobjects.appserver.*;
import com.webobjects.eocontrol.*;
import com.webobjects.directtoweb.*;
import er.directtoweb.ERDirectToWeb;
import er.extensions.*;
import er.wrox.User;
import java.util.Enumeration;

public class Application extends ERXApplication {

    public static void main(String argv[]) {
        WOApplication.main(argv, Application.class);
    }

    public static final NSArray AllLooks = new NSArray("Ugly");

    public NSArray looks() {
        return AllLooks;
    }

    public Application() {
        statisticsStore().setPassword("4AppStats");
        setDefaultRequestHandler(requestHandlerForKey(directActionRequestHandlerKey()));
        User.registerUserPreferenceHandler();
    }

    public WOResponse handleException(Exception exception, WOContext context) {
        NSMutableDictionary extraInfo = new NSMutableDictionary();
        if (context != null && context.page() != null) {
            extraInfo.setObjectForKey(context.page().toString(), "currentPage");
            extraInfo.setObjectForKey(context.request().uri(), "uri");
            if (context.page() instanceof D2WComponent) {
                D2WContext c = ((D2WComponent) context.page()).d2wContext();
                String pageConfiguration = (String) c.valueForKey("pageConfiguration");
                if (pageConfiguration != null) extraInfo.setObjectForKey(pageConfiguration, "pageConfiguration");
            }
            if (context.session() != null && context.session().statistics() != null) {
                extraInfo.setObjectForKey(context.session().statistics(), "previousPageList");
            }
        }
        reportException(exception, extraInfo);
        return super.handleException(exception, context);
    }

    public WOResponse reportException(Throwable exception, NSDictionary extraInfo) {
        try {
            NSLog.out.appendln("    **** Caught: " + exception);
            NSLog.out.appendln("         Extra Information " + extraInfo);
            exception.printStackTrace();
            if (WOApplication.application().isCachingEnabled()) {
            }
        } catch (Throwable u) {
            NSLog.out.appendln("************ Caught exception " + u + " trying to report another one: " + exception);
            NSLog.out.appendln("** Original exception ");
            exception.printStackTrace();
            NSLog.out.appendln("** Second exception ");
            u.printStackTrace();
        }
        return null;
    }
}
