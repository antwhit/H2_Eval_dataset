import java.io.*;
import java.util.*;
import fibs.*;

public class VouchFibsAction implements FibsAction {

    private String COMMAND = null;

    private String[] errResp;

    private String[] ret;

    public VouchFibsAction(FibsCommandProcessor fcp) {
        this.COMMAND = " says: vouch ";
        this.fcp = fcp;
        this.errResp = new String[1];
        this.ret = new String[3];
    }

    public String getGrepString() {
        return this.COMMAND;
    }

    private FibsCommandProcessor fcp;

    private String fromWho = null;

    private String aboutWho = null;

    private static String shouldBeSays = "says:";

    private static String shouldBeVouch = "vouch";

    private String fcpResponse;

    public String[] action(String command, OutputStream log) {
        StringTokenizer st = new StringTokenizer(command);
        try {
            fromWho = st.nextToken();
            if (shouldBeSays.compareTo(st.nextToken()) != 0) {
                return (null);
            }
            if (shouldBeVouch.compareTo(st.nextToken()) != 0) {
                errResp[0] = " I didn't understand you. Try: tell RepBot help";
                return (errResp);
            }
        } catch (NoSuchElementException e) {
            return (null);
        }
        try {
            aboutWho = st.nextToken();
            if (aboutWho.compareToIgnoreCase(fromWho) == 0) {
                errResp[0] = "tell " + fromWho + " I am afraid that is against the rules.";
                return (errResp);
            }
            if (RepBot.banned.indexOf("," + fromWho + ",") >= 0) {
                errResp[0] = "tell " + fromWho + " You are banned from using me";
                return (errResp);
            }
            fcpResponse = fcp.vouch(fromWho, aboutWho);
            if (fcpResponse != null) {
            }
            ret[0] = "tell " + fromWho + " Received your vouch for " + aboutWho + ", processing...";
            ret[1] = "whois " + fromWho;
            ret[2] = "whois " + aboutWho;
            return (ret);
        } catch (NoSuchElementException e) {
            errResp[0] = "tell " + fromWho + " Try: tell RepBot vouch <username>";
            return (errResp);
        }
    }
}
