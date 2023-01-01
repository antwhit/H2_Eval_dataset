import com.webobjects.appserver.*;

public class AjaxExampleComponent extends WOComponent {

    private String _title;

    public AjaxExampleComponent(WOContext context) {
        super(context);
    }

    public void setTitle(String title) {
        _title = title;
    }

    public String title() {
        return _title;
    }
}
