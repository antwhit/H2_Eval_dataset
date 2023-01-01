import java.awt.*;
import java.awt.event.*;
import java.util.EventObject;

public class WantToSaveReportDialog extends Dialog {

    class SymWindow extends WindowAdapter {

        public void windowClosing(WindowEvent windowevent) {
            Object obj = windowevent.getSource();
            if (obj == WantToSaveReportDialog.this) {
                QuitRetroDialog_WindowClosing(windowevent);
            }
        }

        SymWindow() {
        }
    }

    class SymAction implements ActionListener {

        public void actionPerformed(ActionEvent actionevent) {
            Object obj = actionevent.getSource();
            if (obj == noButton) {
                noButton_Clicked(actionevent);
                return;
            }
            if (obj == yesButton) {
                yesButton_Clicked(actionevent);
                return;
            }
            if (obj == cancelbutton) {
                cancelbutton_ActionPerformed(actionevent);
            }
        }

        SymAction() {
        }
    }

    public WantToSaveReportDialog(Frame frame, boolean flag) {
        super(frame, flag);
        fComponentsAdjusted = false;
        setLayout(null);
        setVisible(false);
        setSize(337, 135);
        cancelbutton = new Button();
        cancelbutton.setLabel("Cancel");
        cancelbutton.setBounds(137, 63, 60, 23);
        add(cancelbutton);
        yesButton = new Button();
        yesButton.setLabel("Discard");
        yesButton.setBounds(27, 64, 79, 22);
        yesButton.setFont(new Font("Dialog", 1, 12));
        add(yesButton);
        noButton = new Button();
        noButton.setLabel(" Save  ");
        noButton.setBounds(233, 64, 79, 22);
        noButton.setFont(new Font("Dialog", 1, 12));
        add(noButton);
        label1 = new Label("Report File modified. Do you want to save?", 1);
        label1.setBounds(6, 9, 298, 24);
        add(label1);
        setTitle("Search XML - Save");
        SymWindow symwindow = new SymWindow();
        addWindowListener(symwindow);
        SymAction symaction = new SymAction();
        noButton.addActionListener(symaction);
        yesButton.addActionListener(symaction);
        cancelbutton.addActionListener(symaction);
    }

    public void addNotify() {
        Dimension dimension = getSize();
        super.addNotify();
        if (fComponentsAdjusted) {
            return;
        }
        setSize(insets().left + insets().right + dimension.width, insets().top + insets().bottom + dimension.height);
        Component acomponent[] = getComponents();
        for (int i = 0; i < acomponent.length; i++) {
            Point point = acomponent[i].getLocation();
            point.translate(insets().left, insets().top);
            acomponent[i].setLocation(point);
        }
        fComponentsAdjusted = true;
    }

    public WantToSaveReportDialog(Frame frame, String s, boolean flag) {
        this(frame, flag);
        setTitle(s);
    }

    public void setVisible(boolean flag) {
        if (flag) {
            Rectangle rectangle = getParent().getBounds();
            Rectangle rectangle1 = getBounds();
            setLocation(rectangle.x + (rectangle.width - rectangle1.width) / 2, rectangle.y + (rectangle.height - rectangle1.height) / 2);
        }
        super.setVisible(flag);
    }

    void QuitRetroDialog_WindowClosing(WindowEvent windowevent) {
        dispose();
    }

    void yesButton_Clicked(ActionEvent actionevent) {
        ((XSLTestFrame) getParent()).fileDirty = false;
        dispose();
    }

    void noButton_Clicked(ActionEvent actionevent) {
        ((XSLTestFrame) getParent()).miSave_ActionPerformed(null);
        yesButton_Clicked(null);
    }

    void cancelbutton_ActionPerformed(ActionEvent actionevent) {
        dispose();
    }

    boolean fComponentsAdjusted;

    Button cancelbutton;

    Button yesButton;

    Button noButton;

    Label label1;
}
