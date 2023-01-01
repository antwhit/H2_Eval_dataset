import java.awt.event.KeyEvent;

/**
 * keyboard handler for the main window
 *
 * initializes a generic keyboard handler and assign specified key events to
 * "function-pointers" resp. classes and function names
 *
 * @author Andreas Ziermann
 *
 */
class AppKeyboardHandler extends AzKeyboardHandler {

    private AzApplication app;

    private ImageViewPanel pic;

    private Object[][] initTable;

    AppKeyboardHandler(AzApplication app, ImageView iv) {
        this.app = app;
        this.pic = iv.getViewPanel();
        final Object[][] initTable = { { KeyEvent.VK_LEFT, 0, pic, "left" }, { KeyEvent.VK_RIGHT, 0, pic, "right" }, { KeyEvent.VK_UP, 0, pic, "up" }, { KeyEvent.VK_DOWN, 0, pic, "down" }, { KeyEvent.VK_SPACE, 0, iv, "next" }, { KeyEvent.VK_BACK_SPACE, 0, iv, "prev" }, { KeyEvent.VK_PAGE_UP, 0, pic, "zoomIn" }, { KeyEvent.VK_PAGE_DOWN, 0, pic, "zoomOut" }, { KeyEvent.VK_L, 0, iv, "rotateLeft" }, { KeyEvent.VK_R, 0, iv, "rotateRight" }, { KeyEvent.VK_I, 0, pic, "toggleFileInfo" }, { KeyEvent.VK_T, 0, iv, "toggleTimer" }, { KeyEvent.VK_ESCAPE, 0, app, "exit" }, { KeyEvent.VK_F1, 0, app, "openHelpWindow" }, { KeyEvent.VK_F11, 0, app, "toggleWindowState" }, { KeyEvent.VK_DELETE, 0, iv, "deleteFile" }, { KeyEvent.VK_M, KeyEvent.ALT_MASK, iv, "moveFileTo" } };
        inititialize(initTable);
        this.initTable = initTable;
    }

    Object[][] getInitTable() {
        return initTable;
    }
}
