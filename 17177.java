import java.awt.*;

public class StateMatchDialog extends YesNoDialog {

    public StateMatchDialog(AppMain app, OperationCommand command) {
        super(app.get_gui().get_frame(), "Allow Match?", "The operation `" + command.name() + "' leads to\n" + "some successor(s) matching some other\n" + "existing state(s)\n \n" + "Do you wish to allow the match to occur?", "Allow Match", "Prevent Match", null);
    }

    boolean answer;

    public void yes() {
        answer = true;
    }

    public void no() {
        answer = false;
    }

    public boolean answer() {
        return answer;
    }
}
