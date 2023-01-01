import gcg_obj;
import java.awt.*;

class Check_obj extends gcg_obj {

    int Value;

    Checkbox field;

    public int Id() {
        return 8;
    }

    public void copy(gcg_obj OldCtrl) {
        Check_obj obj = (Check_obj) OldCtrl;
        field = obj.field;
    }
}

;
