import java.util.*;
import java.net.*;
import java.io.*;
import borknet_services.core.*;

/**
 * The server communication class of the Q IRC Botot.
 * @author Ozafy - ozafy@borknet.org - http://www.borknet.org
 */
public class Commands {

    private ArrayList<Object> cmds = new ArrayList<Object>();

    private ArrayList<String> cmdn = new ArrayList<String>();

    private Core C;

    private P Bot;

    private String numeric = "";

    private String botnum = "";

    public Commands(Core C, P Bot) {
        this.C = C;
        this.Bot = Bot;
        numeric = Bot.get_num();
        botnum = Bot.get_corenum();
        cmds = Bot.getCmds();
        cmdn = Bot.getCmdn();
    }

    public void privmsg(String target, String username, String message) {
        if (!target.equals(numeric) && !target.equals(numeric + botnum) && !target.equalsIgnoreCase(Bot.get_nick() + "@" + Bot.get_host())) return;
        String command = "";
        try {
            String[] result = message.split("\\s");
            command = result[0].toLowerCase();
        } catch (ArrayIndexOutOfBoundsException e) {
            command = message.toLowerCase();
        }
        if (command.equals("help")) {
            String cmd = "";
            try {
                String[] result = message.split("\\s");
                cmd = result[1].toLowerCase();
            } catch (ArrayIndexOutOfBoundsException e) {
                C.cmd_notice(numeric, botnum, username, "/msg " + Bot.get_nick() + " help <command>");
                return;
            }
            int compo = cmdn.indexOf(cmd);
            if (compo > -1) {
                Command ccommand = (Command) cmds.get(compo);
                CoreDBControl dbc = C.get_dbc();
                int lev = dbc.getAuthLev(username);
                ccommand.parse_help(C, Bot, numeric, botnum, username, lev);
            } else {
                C.cmd_notice(numeric, botnum, username, "This command is either unknown, or you need to be opered up to use it.");
            }
            return;
        }
        if (command.equals("showcommands")) {
            C.cmd_notice(numeric, botnum, username, "The following commands are available to you:");
            C.cmd_notice(numeric, botnum, username, "For more information on a specific command, type HELP <command>:");
            CoreDBControl dbc = C.get_dbc();
            int lev = dbc.getAuthLev(username);
            for (int n = 0; n < cmds.size(); n++) {
                Command ccommand = (Command) cmds.get(n);
                ccommand.showcommand(C, Bot, numeric, botnum, username, lev);
            }
            C.cmd_notice(numeric, botnum, username, "End of list.");
            return;
        }
        int compo = cmdn.indexOf(command);
        if (command.startsWith("\1")) {
            compo = cmdn.indexOf(command.replace("\1", ""));
        }
        if (compo > -1) {
            Command ccommand = (Command) cmds.get(compo);
            ccommand.parse_command(C, Bot, numeric, botnum, username, message);
        } else {
            C.cmd_notice(numeric, botnum, username, "This command is either unknown, or you need to be opered up to use it.");
            C.cmd_notice(numeric, botnum, username, "/msg " + Bot.get_nick() + " showcommands");
        }
    }
}
