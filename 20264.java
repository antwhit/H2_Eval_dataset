import java.io.*;
import java.util.*;
import borknet_services.core.*;

/**
 * Class to load configuration files.
 * @author Ozafy - ozafy@borknet.org - http://www.borknet.org
 */
public interface Command {

    void parse_command(Core C, Tutor Bot, String numeric, String botnum, String username, String params);

    void parse_help(Core C, Tutor Bot, String numeric, String botnum, String username, int lev);

    void showcommand(Core C, Tutor Bot, String numerc, String botnum, String username, int lev);
}
