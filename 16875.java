import java.awt.*;

class clipboardFrame extends Frame {

    TextArea ta;

    Button clear, dismiss;

    String selection;

    vncviewer v;

    clipboardFrame(vncviewer v1) {
        super("TightVNC Clipboard");
        v = v1;
        GridBagLayout gridbag = new GridBagLayout();
        setLayout(gridbag);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        ta = new TextArea(5, 40);
        gridbag.setConstraints(ta, gbc);
        add(ta);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.gridwidth = 1;
        clear = new Button("Clear");
        gridbag.setConstraints(clear, gbc);
        add(clear);
        dismiss = new Button("Dismiss");
        gridbag.setConstraints(dismiss, gbc);
        add(dismiss);
        pack();
    }

    void setCutText(String text) {
        selection = text;
        ta.setText(text);
        if (isVisible()) {
            ta.selectAll();
        }
    }

    public boolean lostFocus(Event evt, Object arg) {
        if ((selection != null) && !selection.equals(ta.getText())) {
            selection = ta.getText();
            v.setCutText(selection);
        }
        return true;
    }

    public boolean action(Event evt, Object arg) {
        if (evt.target == dismiss) {
            hide();
            return true;
        } else if (evt.target == clear) {
            ta.setText("");
            return true;
        }
        return false;
    }
}
