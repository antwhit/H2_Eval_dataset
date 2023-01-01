import com.webobjects.foundation.*;
import com.webobjects.appserver.*;
import com.webobjects.eocontrol.*;
import com.webobjects.eoaccess.*;

public class Home extends WOComponent {

    public Home(WOContext context) {
        super(context);
    }

    public String email() {
        return Main.EMAIL;
    }
}
