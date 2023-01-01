import java.io.*;
import java.util.*;
import java.text.*;
import borknet_services.core.*;

/**
 * Class to load configuration files.
 * @author Ozafy - ozafy@borknet.org - http://www.borknet.org
 */
public class Ungline implements Command {

    /**
     * Constructs a Loader
     * @param debug		If we're running in debug.
     */
    public Ungline() {
    }

    public void parse_command(Core C, Q Bot, DBControl dbc, String numeric, String botnum, String target, String username, String params) {
        String user[] = dbc.getUserRow(username);
        if (user[4].equals("0")) {
            C.cmd_notice(numeric, botnum, username, "You are not AUTH'd.");
            return;
        }
        String auth[] = dbc.getAuthRow(user[4]);
        if (user[5].equals("1") && Integer.parseInt(auth[3]) > 99) {
            String[] result = params.split("\\s");
            try {
                String host = result[1].replace("*", "%");
                dbc.delGline(host);
                C.report(auth[0] + " removed all glines matching '" + host + "' .");
                C.cmd_notice(numeric, botnum, username, "Done.");
                return;
            } catch (ArrayIndexOutOfBoundsException e) {
                C.cmd_notice(numeric, botnum, username, "/msg " + Bot.get_nick() + " ungline <pattern>");
                return;
            }
        } else {
            C.cmd_notice(numeric, botnum, username, "This command is either unknown, or you need to be opered up to use it.");
            return;
        }
    }

    public void parse_help(Core C, Q Bot, String numeric, String botnum, String username, int lev) {
        if (lev > 99) {
            C.cmd_notice(numeric, botnum, username, "/msg " + Bot.get_nick() + " ungline <pattern>");
        } else {
            C.cmd_notice(numeric, botnum, username, "This command is either unknown, or you need to be opered up to use it.");
        }
    }

    public void showcommand(Core C, Q Bot, String numeric, String botnum, String username, int lev) {
        if (lev > 99) {
            C.cmd_notice(numeric, botnum, username, "UNGLINE             Removes all glines matching the given pattern. - level 100.");
        }
    }
}
