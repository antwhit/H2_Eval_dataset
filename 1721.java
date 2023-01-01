import com.webobjects.foundation.*;
import com.webobjects.appserver.*;
import com.webobjects.eocontrol.*;
import com.webobjects.eoaccess.*;
import er.extensions.*;

public class Main extends WOComponent {

    /** logging support */
    public static final ERXLogger log = ERXLogger.getERXLogger(Main.class);

    public Main(WOContext context) {
        super(context);
    }

    protected String languageName;

    protected String nameString;

    public String localizedDisplayStringForLanguageName() {
        return ((Session) session()).localizer().localizedStringForKeyWithDefault(languageName);
    }

    /** @TypeInfo java.lang.String */
    public NSDictionary localizerCreatedKeys() {
        return ((Session) session()).localizer().createdKeys();
    }

    public WOComponent sayHello() {
        return null;
    }

    public String languageName() {
        return languageName;
    }

    public void setLanguageName(String newLanguageName) {
        languageName = newLanguageName;
    }

    public String nameString() {
        return nameString;
    }

    public void setNameString(String newNameString) {
        nameString = newNameString;
    }
}
