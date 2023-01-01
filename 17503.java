import ui.*;
import gp.*;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.Button;
import java.awt.Event;
import java.awt.Label;
import java.awt.Component;
import java.awt.Color;

/**
* This is the modal dialog for inspecting and editing some
* properties of the genetic algorithm.
*
* @version 1.0
* @author Hans U. Gerber (<a href="mailto:gerber@ifh.ee.ethz.ch">gerber@ifh.ee.ethz.ch</a>)
*/
public class SettingsDialog extends Dialog {

    private GP gp;

    private Button okButton;

    private Button cancelButton;

    private Label status;

    private void addNumberField(NumberUI param, int x, int y) {
        addComponent(new Label(param.getLabel(), Label.RIGHT), new Rectangle(x - 143, y, 140, 14));
        addComponent(param, new Rectangle(x, y, 30, 14));
        addComponent(new Label("(" + param.getMin() + " ... " + param.getMax() + ")"), new Rectangle(x + 40, y, 50, 14));
    }

    private void addChoiceField(ChoiceUI param, int x, int y) {
        addComponent(new Label(param.getLabel(), Label.RIGHT), new Rectangle(x - 143, y, 140, 14));
        addComponent(param, new Rectangle(x, y, 90, 14));
    }

    /**
	*	Creates the dialog with all its components.
	* Note that the dialog uses Microsoft's DialogLayout manager.
	* All coordinates are measured in "Dialog Units", a measure
	* based on the font size.
	*/
    SettingsDialog(Frame parent, GP gp) {
        super(parent, new Rectangle(8, 8, 280, 282), "GP Settings", true);
        this.gp = gp;
        setResizable(false);
        okButton = new Button("OK");
        addComponent(okButton, new Rectangle(5, 236, 50, 14));
        cancelButton = new Button("Cancel");
        addComponent(cancelButton, new Rectangle(60, 236, 50, 14));
        addNumberField(gp.populationSize, 140, 3);
        addNumberField(gp.maxDepthForNewIndividuals, 140, 18);
        addNumberField(gp.crossoverFraction, 140, 33);
        addNumberField(gp.fitnessProportionateReproFraction, 140, 48);
        addNumberField(gp.mutationFraction, 140, 63);
        addNumberField(gp.maxDepthForIndividualsAfterCrossover, 140, 78);
        addNumberField(gp.maxDepthForNewSubtreesInMutants, 140, 93);
        addChoiceField(gp.methodOfGeneration, 140, 108);
        addChoiceField(gp.methodOfSelection, 140, 123);
        addComponent(new Label(gp.fitnessCases.getLabel()), new Rectangle(5, 140, 50, 12));
        addComponent(gp.fitnessCases, new Rectangle(5, 152, 98, 65));
        addComponent(new Label(gp.functionSet.getLabel()), new Rectangle(110, 140, 50, 12));
        addComponent(gp.functionSet, new Rectangle(110, 152, 80, 65));
        addComponent(new Label(gp.terminalSet.getLabel()), new Rectangle(195, 140, 50, 12));
        addComponent(gp.terminalSet, new Rectangle(195, 152, 80, 65));
        status = new Label();
        addComponent(status, new Rectangle(5, 222, 240, 10));
    }

    /**
	* Is called whenever the user clicks a command button.
	* On the "OK" button, the dialog validates all its fields.
	* If any field is found invalid, the error message returned
	* by the offending component is displayed.
	* If all fields are valid, their contents are accepted.
	*/
    public boolean action(Event evt, Object what) {
        if (evt.target == okButton) {
            String validateMsg = onApply();
            if (validateMsg == null) {
                status.setText("");
            } else {
                status.setText(validateMsg);
            }
            return true;
        }
        if (evt.target == cancelButton) {
            onCancel();
            return true;
        }
        return false;
    }

    public String onApply() {
        String result = super.onApply();
        if (result == null) {
            gp.settingsChanged();
        }
        return result;
    }
}
