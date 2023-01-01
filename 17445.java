/**
 * GTPInterface allows interaction through the Go Text Protocol
 */
class GoInterface {

    private int protocol_version = 0;

    private static final String BOARDSIZE = "boardsize";

    private static final String CLEAR_BOARD = "clear_board";

    private static final String FINAL_SCORE = "final_score";

    private static final String FINAL_STATUS_LIST = "final_status_list";

    private static final String FIXED_HANDICAP = "fixed_handicap";

    private static final String GENMOVE = "genmove";

    private static final String KNOWN_COMMAND = "known_command";

    private static final String KOMI = "komi";

    private static final String LIST_COMMANDS = "list_commands";

    private static final String LOADSGF = "loadsgf";

    private static final String NAME = "name";

    private static final String PLACE_FREE_HANDICAP = "place_free_handicap";

    private static final String PLAY = "play";

    private static final String PROTOCOL_VERSION = "protocol_version";

    private static final String QUIT = "quit";

    private static final String REG_GENMOVE = "reg_genmove";

    private static final String SET_FREE_HANDICAP = "set_free_handicap";

    private static final String SHOWBOARD = "showboard";

    private static final String TIME_LEFT = "time_left";

    private static final String TIME_SETTINGS = "time_settings";

    private static final String UNDO = "undo";

    private static final String VERSION = "version";

    private String[] list_commands = { LIST_COMMANDS, PROTOCOL_VERSION };

    public static void main(String[] args) {
        GoInterface testInterface = new GoInterface();
        if (args.length > 0) {
            System.out.println("Command line parameters detected!");
            if (args[0].equals("list_commands")) {
                System.out.println("User has requested that known commands be listed!");
                testInterface.listCommands("42");
            }
        }
        System.out.println("Done.");
    }

    public String nextCommand() {
        String ID = new String();
        return null;
    }

    private void listCommands(String ID) {
        String line = new String("=" + ID + " ");
        for (int i = 0; i < list_commands.length; i++) {
            System.out.println(line + list_commands[i]);
            line = null;
        }
        System.out.println();
    }
}
