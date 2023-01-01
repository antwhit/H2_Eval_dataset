import java.util.*;

/**
 * @author qarce 
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class CommandObject extends Object {

    String name_ = "";

    String shortDescription_ = "";

    String help_ = "";

    String ops_ = "";

    String[] keyNames_;

    String versionString_ = "0.0";

    public CommandObject() {
        ;
    }

    public String getName() {
        return name_;
    }

    public void setName(String name) {
        name_ = name;
    }

    public String getShortDescription() {
        return (shortDescription_);
    }

    public void setShortDescription(String shortDescription) {
        shortDescription_ = shortDescription;
    }

    public String getHelp() {
        return help_;
    }

    public void setHelp(String help) {
        help_ = help;
    }

    public String getOps() {
        return ops_;
    }

    public void setOps(String ops) {
        ops_ = ops;
    }

    public String[] getKeyNames() {
        return keyNames_;
    }

    public void setKeyNames(String[] keyNames) {
        keyNames_ = keyNames;
    }

    public String getVersionString() {
        return (versionString_);
    }

    public void doCommand(String commandString, Tui parentTui) {
        StringTokenizer strTok = new StringTokenizer(commandString);
        doCommand(strTok, parentTui);
    }

    public void doCommand(StringTokenizer commandTokens, Tui parentTui) {
    }
}
