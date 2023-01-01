import java.io.*;
import java.awt.*;
import java.awt.dnd.*;
import java.awt.event.*;
import java.awt.datatransfer.*;
import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.util.Vector;
import java.util.Collections;

public class JNewsTabDropTargetListener implements DropTargetListener {

    private static final int ALL_ACTIONS = DnDConstants.ACTION_COPY_OR_MOVE | DnDConstants.ACTION_LINK;

    public JNewsTabDropTargetListener() {
        super();
    }

    private void checkDrag(DropTargetDragEvent dtde) {
        Point mouseLoc = dtde.getLocation();
        TabbedPaneUI tpUI = (TabbedPaneUI) JNewsUI.jTab.getUI();
        int index;
        index = tpUI.tabForCoordinate(JNewsUI.jTab, mouseLoc.x, mouseLoc.y);
        if (index == 0 || index == -1 || !dtde.isDataFlavorSupported(JNewsGroupDrag.localXfer)) {
            dtde.rejectDrag();
        } else {
            dtde.acceptDrag(ALL_ACTIONS);
        }
    }

    public void dragEnter(DropTargetDragEvent dtde) {
        checkDrag(dtde);
    }

    public void dragOver(DropTargetDragEvent dtde) {
        checkDrag(dtde);
    }

    public void dragExit(DropTargetEvent dtde) {
    }

    public void dropActionChanged(DropTargetDragEvent dtde) {
    }

    public void drop(DropTargetDropEvent dtde) {
        Transferable t = dtde.getTransferable();
        if (t.isDataFlavorSupported(JNewsGroupDrag.localXfer)) {
            Vector s;
            dtde.acceptDrop(ALL_ACTIONS);
            try {
                TabbedPaneUI tpUI = (TabbedPaneUI) JNewsUI.jTab.getUI();
                JNewsTable jt;
                Point locPt;
                int i;
                s = (Vector) t.getTransferData(JNewsGroupDrag.localXfer);
                dtde.getDropTargetContext().dropComplete(true);
                locPt = dtde.getLocation();
                jt = (JNewsTable) JNewsUI.tables.elementAt(tpUI.tabForCoordinate(JNewsUI.jTab, locPt.x, locPt.y));
                jt.jntList.addAll(s);
                jt.fireDataChanged();
            } catch (IOException e) {
                dtde.rejectDrop();
            } catch (UnsupportedFlavorException e) {
                dtde.rejectDrop();
            }
        } else {
            dtde.rejectDrop();
        }
    }
}
