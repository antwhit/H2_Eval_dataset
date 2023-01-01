import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;

public class LogoutPage extends WOComponent {

    public LogoutPage(WOContext context) {
        super(context);
    }

    public boolean isStateless() {
        return true;
    }
}
