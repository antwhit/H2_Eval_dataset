import java.io.*;
import java.util.*;
import borknet_services.core.*;

/**
 * Class to load configuration files.
 * @author Ozafy - ozafy@borknet.org - http://www.borknet.org
 */
public class Version implements Command {

    /**
     * Constructs a Loader
     * @param debug		If we're running in debug.
     */
    public Version() {
    }

    public void parse_command(Core C, T Bot, String numeric, String botnum, String username, String params) {
        String version1 = C.get_version();
        String version2 = "Trojanscan Module (C) Laurens Panier (Ozafy) & BorkNet Dev-Com - http://www.borknet.org";
        if (params.startsWith("\1")) {
            version1 = "\1VERSION " + version1 + "\1";
            version2 = "\1VERSION " + version2 + "\1";
        }
        C.cmd_notice(numeric, botnum, username, version1);
        C.cmd_notice(numeric, botnum, username, version2);
    }

    public void parse_help(Core C, T Bot, String numeric, String botnum, String username, int lev) {
        C.cmd_notice(numeric, botnum, username, "/msg " + Bot.get_nick() + " version");
    }

    public void showcommand(Core C, T Bot, String numeric, String botnum, String username, int lev) {
        C.cmd_notice(numeric, botnum, username, "version - Shows the version.");
    }
}
