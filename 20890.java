import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class OptionDialog extends JDialog {

    protected JTextField subjURL;

    protected JTextField creator;

    protected JTextField provider;

    protected JButton okButton;

    protected JButton cancelButton;

    protected class OkAction extends AbstractAction {

        protected OptionDialog dlg;

        public OkAction(OptionDialog dlg) {
            super("Ok");
            this.dlg = dlg;
        }

        public void actionPerformed(ActionEvent e) {
            dlg.ok();
        }
    }

    protected class CancelAction extends AbstractAction {

        protected OptionDialog dlg;

        public CancelAction(OptionDialog dlg) {
            super("Cancel");
            this.dlg = dlg;
        }

        public void actionPerformed(ActionEvent e) {
            dlg.cancel();
        }
    }

    protected JComponent createForm() {
        JComponent form = new JPanel();
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        form.setLayout(gb);
        JLabel l = new JLabel("Subjects URL:");
        c.fill = GridBagConstraints.NONE;
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.weightx = 0;
        gb.setConstraints(l, c);
        form.add(l);
        subjURL = new JTextField();
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.weightx = 1.0;
        gb.setConstraints(subjURL, c);
        form.add(subjURL);
        l = new JLabel("Creator:");
        c.fill = GridBagConstraints.NONE;
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.weightx = 0;
        gb.setConstraints(l, c);
        form.add(l);
        creator = new JTextField();
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.weightx = 1.0;
        gb.setConstraints(creator, c);
        form.add(creator);
        l = new JLabel("Provider:");
        c.fill = GridBagConstraints.NONE;
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.weightx = 0;
        gb.setConstraints(l, c);
        form.add(l);
        provider = new JTextField();
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.weightx = 1.0;
        gb.setConstraints(provider, c);
        form.add(provider);
        return form;
    }

    protected void formToPrefs() {
        Context.get().setPrefSubjectSource(subjURL.getText());
        Context.get().setPrefDefaultCreator(creator.getText());
        Context.get().setPrefDefaultProvider(provider.getText());
    }

    protected void prefsToForm() {
        subjURL.setText(Context.get().getPrefSubjectSource());
        creator.setText(Context.get().getPrefDefaultCreator());
        provider.setText(Context.get().getPrefDefaultProvider());
    }

    public void ok() {
        formToPrefs();
        setVisible(false);
    }

    public void cancel() {
        setVisible(false);
    }

    public OptionDialog(Frame frame, String title) {
        super(frame, title, true);
        JComponent form = createForm();
        okButton = new JButton(new OkAction(this));
        cancelButton = new JButton(new CancelAction(this));
        getRootPane().setDefaultButton(okButton);
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        buttons.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttons.add(Box.createHorizontalGlue());
        buttons.add(okButton);
        buttons.add(Box.createRigidArea(new Dimension(10, 0)));
        buttons.add(cancelButton);
        getContentPane().add(form, BorderLayout.CENTER);
        getContentPane().add(buttons, BorderLayout.SOUTH);
        addComponentListener(new ComponentAdapter() {

            public void componentShown(ComponentEvent e) {
                prefsToForm();
            }
        });
        pack();
    }
}
