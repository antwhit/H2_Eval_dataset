import java.util.*;
import java.io.*;

/**
 * @author qarce 
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class CommandHelp extends CommandObject {

    Hashtable comTable_;

    OutputStream outStream_;

    Vector seenComTable_;

    CommandObject comObjTmp_;

    Enumeration enum_;

    String comKey_;

    public CommandHelp() {
        keyNames_ = new String[3];
        keyNames_[0] = "help";
        keyNames_[1] = "h";
        keyNames_[2] = "?";
        name_ = "help";
        shortDescription_ = "Display a list of the commands with there short descriptions";
        seenComTable_ = new Vector(4, 1);
    }

    public void doCommand(StringTokenizer comTok, Tui parentTui) {
        comTable_ = parentTui.getCommandTable();
        outStream_ = parentTui.getOutStream();
        enum_ = comTable_.keys();
        seenComTable_.removeAllElements();
        comObjTmp_ = null;
        String spaces = "\t";
        String newline = "\n";
        String nextTok = "";
        if (comTok.hasMoreElements()) {
            nextTok = new String(comTok.nextToken());
            nextTok.trim();
        }
        while (enum_.hasMoreElements()) {
            try {
                comKey_ = (String) enum_.nextElement();
                comObjTmp_ = (CommandObject) comTable_.get(comKey_);
                if ((Character.isLetter(nextTok.charAt(0))) && (comKey_.equalsIgnoreCase(nextTok))) {
                    outStream_.write(("\nName:\n").getBytes());
                    String[] comAliases = comObjTmp_.getKeyNames();
                    for (int index = 0; index < comAliases.length; index++) {
                        outStream_.write(("\t" + comAliases[index] + "\n").getBytes());
                    }
                    outStream_.write(("\nSynopsis:\n").getBytes());
                    outStream_.write(("\t" + comObjTmp_.getName() + "    " + comObjTmp_.getOps() + "\n").getBytes());
                    outStream_.write(("\nDescription:\n").getBytes());
                    outStream_.write(("\t" + comObjTmp_.getShortDescription() + "\n").getBytes());
                } else if ((!seenComTable_.contains(comObjTmp_)) && (!Character.isLetter(nextTok.charAt(0)))) {
                    seenComTable_.add(comObjTmp_);
                    outStream_.write(newline.getBytes());
                    outStream_.write(comObjTmp_.getName().getBytes());
                    outStream_.write(spaces.getBytes());
                    outStream_.write(comObjTmp_.getShortDescription().getBytes());
                }
            } catch (IOException ioe) {
            }
        }
        System.out.println("doCommand called for help");
    }
}
