/**
 * Logs messages.
 */
class Log {

    static final boolean DEBUG = false;

    static void debug(String message) {
        if (DEBUG) {
            info(message);
        }
    }

    static void info(String message) {
        System.out.println(message);
    }
}
