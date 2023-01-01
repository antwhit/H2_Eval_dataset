/**
 * generic error handler, which should avoid any System.out.println("") statements
 *
 * handle errors and error messages languages dependend.
 * The output could be sent to STDOUT, redirected to a log file or even shown as popup.
 * @author Andreas Ziermann
 *
 */
class AzError {

    static void log(String who, STR fmt, Object... objects) {
        System.out.print(who + ": ");
        System.out.printf(fmt.str(), objects);
        System.out.println();
    }
}
