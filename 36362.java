import java.awt.*;
import javax.swing.*;
import java.util.Map;
import java.util.Hashtable;
import java.util.List;

public class SecondaryTrack extends BaseTrack {

    SecondaryTrack(CoreModel coreModel) {
        plotModel = new SecondaryModel(coreModel);
        plot = new SecondaryPlot(plotModel);
    }

    public String getName() {
        return "Secondary Structure";
    }

    public String toString() {
        return "Secondary Structure";
    }

    public Map<String, String> getSettings() {
        return new Hashtable<String, String>();
    }

    public Map<String, List> getData() {
        return null;
    }
}
