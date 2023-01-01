import fr.esrf.tangoatk.widget.util.interlock.*;
import javax.swing.*;
import java.awt.event.*;

/** The main Interlock editor frame */
public class ItlkEditor extends NetEditorFrame {

    ItlkNetEditor itlkEditor;

    JMenuItem traceMode;

    JMenuItem editMode;

    JButton bubbleBtn;

    JButton permitBtn;

    JButton joinBtn;

    JButton vccBtn;

    JButton groundBtn;

    public ItlkEditor() {
        setAppTitle("Interlock Simulator Editor");
        itlkEditor = new ItlkNetEditor(this);
        setEditor(itlkEditor);
        traceMode = NetUtils.createMenuItem("Trace mode", 0, 0, this);
        editMode = NetUtils.createMenuItem("Edit mode", 0, 0, this);
        getOptionMenu().add(traceMode, 0);
        getOptionMenu().add(editMode, 1);
        getOptionMenu().add(new JSeparator(), 2);
        JToolBar tb = getToolbar();
        tb.remove(getToobarButton(NetEditorFrame.TOOL_BUBBLE));
        String rPth = "/fr/esrf/tangoatk/widget/util/interlock/gif/";
        bubbleBtn = createIconButton(rPth, "bubble", "Create an interlock object (physical switch)", this);
        tb.add(bubbleBtn, 0);
        permitBtn = createIconButton(rPth, "permit", "Create a permit object (logical sensor)", this);
        tb.add(permitBtn, 1);
        joinBtn = createIconButton(rPth, "join", "Create a join object (intersection point)", this);
        tb.add(joinBtn, 2);
        vccBtn = createIconButton(rPth, "start", "Create a VCC object", this);
        tb.add(vccBtn, 3);
        groundBtn = createIconButton(rPth, "end", "Create a Ground object", this);
        tb.add(groundBtn, 4);
    }

    public void objectClicked(NetEditor src, NetObject obj, MouseEvent e) {
        itlkEditor.swapItlkState(obj);
    }

    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == bubbleBtn) {
            itlkEditor.setCreateMode(NetEditor.CREATE_BUBBLE, ItlkNetEditor.ITLK_BUBBLE);
            getHelpLabel().setText("Interlock creation: Left click to create a interlock object, right click to cancel.");
        } else if (src == permitBtn) {
            itlkEditor.setCreateMode(NetEditor.CREATE_BUBBLE, ItlkNetEditor.SENSOR_BUBBLE);
            getHelpLabel().setText("Sensor creation: Left click to create a sensor object, right click to cancel.");
        } else if (src == joinBtn) {
            itlkEditor.setCreateMode(NetEditor.CREATE_BUBBLE, ItlkNetEditor.JOIN_BUBBLE);
            getHelpLabel().setText("Join creation: Left click to create a join object, right click to cancel.");
        } else if (src == vccBtn) {
            itlkEditor.setCreateMode(NetEditor.CREATE_BUBBLE, ItlkNetEditor.VCC_BUBBLE);
            getHelpLabel().setText("VCC creation: Left click to create a VCC object, right click to cancel.");
        } else if (src == groundBtn) {
            itlkEditor.setCreateMode(NetEditor.CREATE_BUBBLE, ItlkNetEditor.GROUND_BUBBLE);
            getHelpLabel().setText("Ground creation: Left click to create a ground object, right click to cancel.");
        } else if (src == traceMode) {
            itlkEditor.setEditable(false);
        } else if (src == editMode) {
            itlkEditor.setEditable(true);
        } else {
            super.actionPerformed(e);
        }
    }

    public static void main(String[] args) {
        ItlkEditor iE = new ItlkEditor();
        iE.pack();
        iE.setVisible(true);
    }
}
