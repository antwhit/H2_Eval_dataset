import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import er.openid.EROResponse;
import er.openid.IEROResponsePage;

public class FailurePage extends WOComponent implements IEROResponsePage {

    private EROResponse _openIDResponse;

    public FailurePage(WOContext context) {
        super(context);
    }

    public EROResponse openIDResponse() {
        return _openIDResponse;
    }

    public void setOpenIDResponse(EROResponse response) {
        _openIDResponse = response;
    }
}
