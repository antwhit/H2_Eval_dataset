import java.io.*;
import java.util.*;
import java.text.*;
import borknet_services.core.*;

/**
 * Class to load configuration files.
 * @author Ozafy - ozafy@borknet.org - http://www.borknet.org
 */
public class Serverlist implements Command {

    /**
     * Constructs a Loader
     * @param debug		If we're running in debug.
     */
    public Serverlist() {
    }

    public void parse_command(Core C, Q Bot, DBControl dbc, String numeric, String botnum, String target, String username, String params) {
        String user[] = dbc.getUserRow(username);
        if (user[4].equals("0")) {
            C.cmd_notice(numeric, botnum, username, "You are not AUTH'd.");
            return;
        }
        String auth[] = dbc.getAuthRow(user[4]);
        if (user[5].equals("1") && Integer.parseInt(auth[3]) > 99) {
            String[][] servers = C.get_dbc().getServerList();
            C.cmd_notice(numeric, botnum, username, "Network servers:");
            for (int n = 0; n < servers.length; n++) {
                String numericInt = C.base64Decode(servers[n][0]) + "";
                C.cmd_notice(numeric, botnum, username, "(" + servers[n][0] + " - " + numericInt + padding(numericInt, 4) + ") " + servers[n][1] + padding(servers[n][1], 35) + " " + (Boolean.parseBoolean(servers[n][3]) ? "Service" : "Server ") + " Uplink: " + servers[n][2]);
            }
            C.cmd_notice(numeric, botnum, username, "End of list.");
        } else {
            C.cmd_notice(numeric, botnum, username, "This command is either unknown, or you need to be opered up to use it.");
            return;
        }
    }

    public void parse_help(Core C, Q Bot, String numeric, String botnum, String username, int lev) {
        if (lev > 99) {
            C.cmd_notice(numeric, botnum, username, "/msg " + Bot.get_nick() + " serverlist");
        } else {
            C.cmd_notice(numeric, botnum, username, "This command is either unknown, or you need to be opered up to use it.");
        }
    }

    public void showcommand(Core C, Q Bot, String numeric, String botnum, String username, int lev) {
        if (lev > 99) {
            C.cmd_notice(numeric, botnum, username, "SERVERLIST          Shows all currently connected servers. - level 100.");
        }
    }

    private String padding(String s, int l) {
        String p = "";
        for (int i = s.length(); i < l; i++) {
            p += " ";
        }
        return p;
    }
}
