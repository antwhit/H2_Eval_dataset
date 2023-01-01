import com.webobjects.foundation.*;
import com.webobjects.appserver.*;
import com.webobjects.eocontrol.*;
import com.webobjects.eoaccess.*;

public class Main extends WOComponent {

    public Main(WOContext context) {
        super(context);
    }

    public AllTogetherPage goToAllTogetherPage() {
        AllTogetherPage nextPage = (AllTogetherPage) pageWithName("AllTogetherPage");
        return nextPage;
    }

    public SeparatePage goToSeparatePage() {
        SeparatePage nextPage = (SeparatePage) pageWithName("SeparatePage");
        return nextPage;
    }
}
