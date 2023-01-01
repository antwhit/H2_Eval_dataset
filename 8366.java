/**
 *
 * @author  Dominic Stolerman
 */
public class MacroCommand extends Object implements Command {

    /** Creates new MacroCommand */
    public MacroCommand(String macro) {
        this.macro = macro.substring(1);
    }

    public void run(ScriptContext sc) {
        XScripter.runMacro(sc.getView(), (String) sc.getNode().getUserObject(), macro);
    }

    private final String macro;
}
