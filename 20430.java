import java.awt.*;

class authenticationPanel extends Panel {

    Label title, retry, prompt;

    TextField password;

    Button ok;

    public authenticationPanel() {
        title = new Label("VNC Authentication", Label.CENTER);
        title.setFont(new Font("Helvetica", Font.BOLD, 18));
        prompt = new Label("Password:", Label.CENTER);
        password = new TextField(10);
        password.setForeground(Color.black);
        password.setBackground(Color.white);
        password.setEchoCharacter('*');
        ok = new Button("OK");
        retry = new Label("", Label.CENTER);
        retry.setFont(new Font("Courier", Font.BOLD, 16));
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(gridbag);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(title, gbc);
        add(title);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gridbag.setConstraints(retry, gbc);
        add(retry);
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = 1;
        gridbag.setConstraints(prompt, gbc);
        add(prompt);
        gridbag.setConstraints(password, gbc);
        add(password);
        gbc.ipady = 10;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 20, 0, 0);
        gbc.ipadx = 40;
        gridbag.setConstraints(ok, gbc);
        add(ok);
        password.requestFocus();
    }

    public void show() {
        password.requestFocus();
        super.show();
    }

    public TextField getPasswordField() {
        return password;
    }

    public synchronized boolean action(Event evt, Object arg) {
        if ((evt.target == password) || (evt.target == ok)) {
            notify();
            return true;
        }
        return false;
    }

    public void retry() {
        retry.setText("Sorry. Try again.");
        password.setText("");
    }
}
