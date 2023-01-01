import javax.swing.JTree;
import org.gjt.sp.jedit.View;
import org.gjt.sp.jedit.gui.DockableWindowManager;

/**
 * XInsertActions: provides actions that can be bound to keyboard shortcuts
 *
 * @author     Martin Raspe
 * @created    March 5, 2005
 * @modified   $Date: 2005/05/11 12:27:51 $ by $Author: hertzhaft $
 * @version    $Revision: 1.1 $
 */
public class XInsertActions {

    public static void goToXInsert(View view) {
        DockableWindowManager wm = view.getDockableWindowManager();
        if (!wm.isDockableWindowVisible(XInsertPlugin.NAME)) wm.showDockableWindow(XInsertPlugin.NAME);
        XTree xtree = (XTree) wm.getDockableWindow(XInsertPlugin.NAME);
        if (xtree == null) return;
        JTree tree = xtree.getTree();
        if (tree.hasFocus()) {
            view.getTextArea().requestFocus();
            return;
        }
        ;
        xtree.requestFocus();
        tree.requestFocus();
    }

    public static void insertSelected(View view) {
        DockableWindowManager wm = view.getDockableWindowManager();
        XTree xtree = (XTree) wm.getDockableWindow(XInsertPlugin.NAME);
        if (xtree != null) xtree.treeAction();
    }
}
