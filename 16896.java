import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;

public class NoNavPageWrapper extends WOComponent {

    public NoNavPageWrapper(WOContext context) {
        super(context);
    }

    public boolean isStateless() {
        return true;
    }
}
