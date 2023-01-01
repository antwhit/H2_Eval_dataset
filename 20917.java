import java.awt.*;
import java.net.URL;
import symantec.itools.multimedia.ImageViewer;
import symantec.itools.awt.util.dialog.ModalDialog;

public class AttentionDialog extends ModalDialog {

    public AttentionDialog(Frame parent, String title, String message, URL iconURL) {
        super(parent, title);
        if (iconURL != null) {
            ImageViewer img = new ImageViewer(iconURL);
            add(img);
        }
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        setSize(270, 73);
        setVisible(false);
        add(label1);
        label1.setBounds(5, 5, 14, 23);
        okButton.setLabel("OK");
        add(okButton);
        okButton.setBackground(java.awt.Color.lightGray);
        okButton.setBounds(24, 5, 31, 23);
        label1.setText(message);
        SymAction lSymAction = new SymAction();
        okButton.addActionListener(lSymAction);
    }

    public AttentionDialog(Frame parent) {
        this(parent, "Attention", "Event", null);
    }

    public AttentionDialog(Frame parent, boolean modal) {
        this(parent);
    }

    public AttentionDialog(Frame parent, String message, boolean modal) {
        this(parent, "Attention", message, null);
    }

    public void addNotify() {
        Dimension d = getSize();
        super.addNotify();
        if (fComponentsAdjusted) return;
        Insets insets = getInsets();
        setSize(insets.left + insets.right + d.width, insets.top + insets.bottom + d.height);
        Component components[] = getComponents();
        for (int i = 0; i < components.length; i++) {
            Point p = components[i].getLocation();
            p.translate(insets.left, insets.top);
            components[i].setLocation(p);
        }
        fComponentsAdjusted = true;
    }

    boolean fComponentsAdjusted = false;

    java.awt.Label label1 = new java.awt.Label();

    java.awt.Button okButton = new java.awt.Button();

    class SymAction implements java.awt.event.ActionListener {

        public void actionPerformed(java.awt.event.ActionEvent event) {
            Object object = event.getSource();
            if (object == okButton) okButton_ActionPerformed(event);
        }
    }

    void okButton_ActionPerformed(java.awt.event.ActionEvent event) {
        okButton_ActionPerformed_Interaction1(event);
    }

    void okButton_ActionPerformed_Interaction1(java.awt.event.ActionEvent event) {
        try {
            this.dispose();
        } catch (Exception e) {
        }
    }
}
