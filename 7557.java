import net.rim.device.api.ui.*;
import net.rim.device.api.ui.container.*;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.system.*;

public class ListButtonField extends Field implements DrawStyle {

    private int backgroundColour = 0x74914f;

    private int fieldWidth = Display.getWidth();

    private int fieldHeight = 44;

    private int buffer = (Display.getWidth() - 105) / 2;

    private boolean button = false;

    private Bitmap icon;

    private String text;

    private Font fieldFont;

    private boolean enabled = true;

    public ListButtonField(String _text) {
        super(ButtonField.CONSUME_CLICK | Field.FOCUSABLE);
        text = _text;
        button = false;
        fieldFont = FieldFont();
    }

    public void enableButton() {
        if (enabled == true) return;
        enabled = true;
        invalidate();
    }

    public void disableButton() {
        if (enabled == false) return;
        enabled = false;
        invalidate();
    }

    protected void layout(int arg0, int arg1) {
        setExtent(getPreferredWidth(), getPreferredHeight());
    }

    public int getPreferredWidth() {
        fieldWidth = Display.getWidth();
        return fieldWidth;
    }

    public int getPreferredHeight() {
        return fieldHeight;
    }

    public static Font FieldFont() {
        try {
            FontFamily theFam = FontFamily.forName("SYSTEM");
            return theFam.getFont(net.rim.device.api.ui.Font.BOLD, 14);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    protected boolean navigationClick(int status, int time) {
        if (enabled == false) return (true);
        fieldChangeNotify(0);
        return true;
    }

    protected void onFocus(int direction) {
        button = true;
        invalidate();
    }

    protected void onUnfocus() {
        button = false;
        invalidate();
    }

    protected void fieldChangeNotify(int context) {
        try {
            this.getChangeListener().fieldChanged(this, context);
        } catch (Exception exception) {
        }
    }

    protected boolean keyDown(int keycode, int time) {
        if (keycode != 655360 && keycode != 2097152 && keycode != 32768) {
            return false;
        }
        if (enabled == false) return true;
        try {
            this.getChangeListener().fieldChanged(this, 0);
        } catch (Exception exception) {
        }
        return true;
    }

    protected void paint(Graphics g) {
        XYRect extent = getExtent();
        int width = extent.width - 8;
        int height = extent.height - 8;
        g.setColor(backgroundColour);
        g.fillRect(0, 0, getPreferredWidth(), getPreferredHeight());
        g.setColor(0x000000);
        g.drawRoundRect(3, 3, width + 2, height + 2, 44, 44);
        if (!button) g.setColor(0xFFFFFF); else g.setColor(0xd6eff6);
        g.fillRoundRect(4, 4, width, height, 40, 40);
        if (enabled) g.setColor(0x000000); else g.setColor(0xdadada);
        g.drawText(text, (getPreferredWidth() - Font.getDefault().getAdvance(text)) / 2, (getPreferredHeight() - Font.getDefault().getHeight()) / 2);
    }
}
