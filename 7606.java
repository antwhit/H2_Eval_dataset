/** This class has a partial automatic update in the build script.
 * and is used to reference the version.
 * 
 * @link Original code found on 
 *      http://forum.java.sun.com/thread.jspa?forumID=31&threadID=583820
 * @author Mario De Weerd
 */
public final class Version {

    /** Build number (timestamp with format yyyyMMddHHmmssSSS). */
    public static final long BUILD = 20070626235824743L;

    /** Release date of this version (date format dd.MM.yyyy). */
    public static final String DATE = "26.06.2007";

    /**
    * Version number of format x.y.z, with
    * <ul>
    * <li>x = major version
    * <li>y = minor version
    * <li>z = bug fix version
    * </ul>
    */
    public static final String NUMBER = "0.6";

    /** Minimum Java JRE version required. */
    public static final String NUMBER_JAVAMIN = "1.4";

    /** Title of this project. */
    public static final String TITLE = "BT747";
}
