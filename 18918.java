import com.webobjects.appserver.*;
import com.webobjects.foundation.*;
import com.webobjects.eocontrol.*;
import com.webobjects.eoaccess.*;

public class NavBar extends WOComponent {

    public Main openModelAction() {
        Main nextPage = (Main) pageWithName("Main");
        return nextPage;
    }

    public WOComponent helpAction() {
        return null;
    }

    public String helpDirRootPath() {
        return "file://" + WOApplication.application().resourceManager().pathForResourceNamed("help_index.html", null, null);
    }

    public WOComponent openConfiguration() {
        EditConfiguration nextPage = (EditConfiguration) pageWithName("EditConfiguration");
        return nextPage;
    }
}
