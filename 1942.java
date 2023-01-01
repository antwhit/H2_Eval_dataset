import java.awt.Frame;
import cdox.data.Data;
import cdox.data.DataSource;

/**
 * Mp3Source
 *
 * @author jung
 * @version $Revision: 542 $
 * @since 22.03.2003
 */
public class Mp3Source implements DataSource {

    protected Mp3Data data;

    /**
    * 
    */
    public Mp3Source() {
        super();
    }

    public String getDescription() {
        return "MP3 CD";
    }

    public Class getDataType() {
        return Mp3Data.class;
    }

    public void showConfigurationDialog(Frame parent) {
        data = new Mp3FileSourceDialogue(parent).showAndGetMp3Data();
        data.consist();
    }

    public boolean hasConfigurationDialog() {
        return true;
    }

    public Data retrieveData() {
        return data;
    }
}
