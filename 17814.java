import java.util.*;
import userDB.*;

public class WithdrawFibsCommand extends FibsCommand {

    private String returnString;

    private UserDBInterface userDB = null;

    public WithdrawFibsCommand(String fromWho, String aboutWho) {
        super(fromWho, aboutWho);
        this.fromWho = fromWho;
        this.aboutWho = aboutWho;
        try {
            this.userDB = (UserDBInterface) UserDBImplementation.getUserDB();
        } catch (UserDBException udbe) {
        }
    }

    public CommandResult setInfo(String username, String info) throws NumberFormatException {
        if (info == null) {
            return (new CommandResult(true, "tell " + fromWho + " ERROR: user " + aboutWho + " does not exist on FIBS"));
        }
        if (username.compareToIgnoreCase(this.fromWho) == 0) {
            if (username.compareToIgnoreCase(fromWho) != 0) fromWho = username;
            fromExperience = Integer.parseInt(info);
            haveFromInfo = true;
        }
        if (username.compareToIgnoreCase(this.aboutWho) == 0) {
            if (username.compareToIgnoreCase(aboutWho) != 0) aboutWho = username;
            aboutExperience = Integer.parseInt(info);
            haveAboutInfo = true;
        }
        if (haveFromInfo && haveAboutInfo) {
            try {
                userDB.withdraw(fromWho, fromExperience, aboutWho);
                return (new CommandResult(true, "tell " + fromWho + " withdraw succesful"));
            } catch (Exception e) {
                e.printStackTrace();
                return (new CommandResult(false, "tell " + fromWho + " datebase exception"));
            }
        } else {
            return (new CommandResult(false, null));
        }
    }
}
