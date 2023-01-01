/**
 * QUIRKException.java
 * 
 * Description. 
 * 
 * @author Zachary M. Allen (Project 4 - YAQQ), Izzet Envarli (Project 3 - Rational AIM)
 */
public class QUIRKException extends Exception {

    /**
	 * Serial version UID
	 */
    private static final long serialVersionUID = 1L;

    /**
	 * Error code for ill-formed command
	 */
    public static final short ILL_FORMED_COMMAND = 1;

    /**
	 * Description for ill-formed command
	 */
    private static final String ILL_FORMED_COMMAND_DESC = "ERROR: ill-formed command";

    /**
     * Error code for opening index file
     */
    public static final short CANNOT_READ_INDEX_FILE = 2;

    /**
     * Description for opening index file
     */
    private static final String CANNOT_READ_INDEX_FILE_DESC = "ERROR: unable to open index file";

    /**
     * Error code for processing index file
     */
    public static final short CANNOT_PROCESS_INDEX_FILE = 3;

    /**
     * Description for processing index file
     */
    private static final String CANNOT_PROCESS_INDEX_FILE_DESC = "ERROR: unable to process index file";

    /**
     * Error code for opening stop word file
     */
    public static final short CANNOT_READ_STOP_WORD_FILE = 4;

    /**
     * Description for opening stop word file
     */
    private static final String CANNOT_READ_STOP_WORD_FILE_DESC = "ERROR: unable to open stop word file";

    /**
     * Error code for processing stop word file
     */
    public static final short CANNOT_PROCESS_STOP_WORD_FILE = 5;

    /**
     * Description for processing stop word file
     */
    private static final String CANNOT_PROCESS_STOP_WORD_FILE_DESC = "ERROR: unable to process stop word file";

    /**
     * Error code for out-of-date index
     */
    public static final short OUT_OF_DATE_INDEX = 6;

    /**
     * Description for out-of-date index
     */
    private static final String OUT_OF_DATE_INDEX_DESC = "ERROR: out-of-date index file";

    /**
     * Error code for this exception
     */
    private short errorCode;

    /**
     * Creates a new instance of QUIRKException
     * @param    errorCode    The error code that generated this exception.
     */
    public QUIRKException(short newErrorCode) {
        this.errorCode = newErrorCode;
    }

    /**
     * Returns a descriptive message of the error that caused this exception.
     * Overrides the default behavior inherited from the Exception class.
     */
    public String getMessage() {
        String returnVal = "";
        if (errorCode == ILL_FORMED_COMMAND) {
            returnVal = ILL_FORMED_COMMAND_DESC;
        } else if (errorCode == CANNOT_READ_INDEX_FILE) {
            returnVal = CANNOT_READ_INDEX_FILE_DESC;
        } else if (errorCode == CANNOT_PROCESS_INDEX_FILE) {
            returnVal = CANNOT_PROCESS_INDEX_FILE_DESC;
        } else if (errorCode == CANNOT_READ_STOP_WORD_FILE) {
            returnVal = CANNOT_READ_STOP_WORD_FILE_DESC;
        } else if (errorCode == CANNOT_PROCESS_STOP_WORD_FILE) {
            returnVal = CANNOT_PROCESS_STOP_WORD_FILE_DESC;
        } else if (errorCode == OUT_OF_DATE_INDEX) {
            returnVal = OUT_OF_DATE_INDEX_DESC;
        }
        return returnVal;
    }
}
