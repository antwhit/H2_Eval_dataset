import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserDataModel extends BasePlotModel implements IView {

    static final int DISPLAY_DYNAMIC = 1;

    static final int DISPLAY_FIXED = 2;

    static final int DISPLAY_THRESHOLD = 3;

    int windowSize;

    int displayType;

    int displayThreshold;

    public UserDataModel(CoreModel m) {
        assert m != null;
        coreModel = m;
        this.views = new CopyOnWriteArrayList<IView>();
        this.displayType = DISPLAY_DYNAMIC;
        this.displayThreshold = 15;
        this.windowSize = 1;
        coreModel.addView(this);
    }

    public void setDisplayType(int d) {
        if (d != displayType) {
            displayType = d;
            this.updateAllViews();
        }
    }

    public int getDisplayType() {
        return displayType;
    }

    public int getWindowSize() {
        return this.windowSize;
    }

    public void setWindowSize(int size) {
        if (this.windowSize != size) {
            this.windowSize = size;
            this.updateAllViews();
        }
    }

    public void setDisplayThreshold(int t) {
        if (t != displayThreshold) {
            displayThreshold = t;
            this.updateAllViews();
        }
    }

    public int getDisplayThreshold() {
        return displayThreshold;
    }
}
