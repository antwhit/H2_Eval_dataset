import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;

public class PageWrapper extends WOComponent {

    public PageWrapper(WOContext context) {
        super(context);
    }

    public boolean isStateless() {
        return true;
    }
}
