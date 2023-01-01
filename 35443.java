import java.util.*;
import userDB.*;

public class ListFibsCommand extends FibsCommand {

    private UserDBInterface userDB = null;

    public ListFibsCommand(String fromWho, String aboutWho) {
        super(fromWho, aboutWho);
        this.fromWho = fromWho;
        this.aboutWho = aboutWho;
        try {
            this.userDB = UserDBImplementation.getUserDB();
        } catch (UserDBException udbe) {
        }
    }

    public CommandResult setInfo(String username, String info) throws NumberFormatException {
        if (info == null) {
            return (new CommandResult(true, "tell " + fromWho + " ERROR: user " + aboutWho + " does not exist on FIBS"));
        }
        if (username.compareToIgnoreCase(this.fromWho) == 0) {
            if (username.compareToIgnoreCase(fromWho) != 0) fromWho = username;
            this.fromExperience = Integer.parseInt(info);
            this.haveFromInfo = true;
        }
        if (username.compareToIgnoreCase(this.aboutWho) == 0) {
            aboutWho = username;
            System.out.println("LIST: aboutWho corrected=" + aboutWho);
            this.aboutExperience = Integer.parseInt(info);
            this.haveAboutInfo = true;
        }
        if (this.haveFromInfo && this.haveAboutInfo) {
            ret = new StringBuffer("tell " + fromWho + " " + aboutWho);
            try {
                tempResp = userDB.getComplainers(aboutWho);
                ret.append(" complainers:[" + stripExtraCommas(tempResp) + "]");
                tempResp = userDB.getVouchers(aboutWho);
                ret.append(" vouchers:[" + stripExtraCommas(tempResp) + "]");
            } catch (Exception e) {
                ret.append(": no complainers or vouchers, ");
            }
            try {
                ret.append(" complaints:[" + userDB.getComplaints(aboutWho) + "] vouches:[" + userDB.getVouches(aboutWho) + "]");
            } catch (Exception e) {
                ret.append("database exception:" + e.getMessage());
            }
            return (new CommandResult(true, ret.toString()));
        } else {
            return (new CommandResult(false, null));
        }
    }

    private StringBuffer ret = null;

    private String tempResp = null;

    private String stripExtraCommas(String list) {
        int end = list.length() - 1;
        while ((end >= 0) && (list.charAt(end)) == ',') end--;
        return (list.substring(0, end + 1));
    }

    private String resp = null;
}
