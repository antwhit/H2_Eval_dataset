public class AIMException extends Exception {

    private static final long serialVersionUID = -2023548378659665441L;

    public static final short WORD_DEPTH_OVERFLOW = 0;

    private static final String WORD_DEPTH_OVERFLOW_DESC = "ERROR: too many non-unique words";

    public static final short WORD_BREADTH_OVERFLOW = 1;

    private static final String WORD_BREADTH_OVERFLOW_DESC = "ERROR: too many unique words";

    public static final short FILE_NOT_FOUND = 2;

    private static final String FILE_NOT_FOUND_DESC = "ERROR: unable to read input file";

    public static final short CANNOT_READ_FILE = 3;

    private static final String CANNOT_READ_FILE_DESC = "ERROR: unable to process input file";

    public static final short INVALID_STOPWORD_FILE = 4;

    private static final String INVALID_STOPWORD_FILE_DESC = "ERROR: unable to locate stop-word file";

    public static final short CANNOT_READ_STOPWORD_FILE = 5;

    private static final String CANNOT_READ_STOPWORD_FILE_DESC = "ERROR: unable to process stop-word file";

    public static final short BAD_COMMAND = 6;

    private static final String BAD_COMMAND_DESC = "ERROR: the -b or -s option can only be specified once";

    public static final short CANT_WRITE = 7;

    private static final String CANT_WRITE_DESC = "ERROR: Cannot write index to file AIM.out";

    public static final short NO_FILES = 7;

    private static final String NO_FILES_DESC = "ERROR: No files have been specified";

    public static final short STOPFILE_NOT_FOUND = 8;

    private static final String STOPFILE_NOT_FOUND_DESC = "ERROR: unable to open stop word file";

    private short errorCode;

    /**
     * Creates a new instance of HistoException
     * @param errorCode The error code that generated this exception.
     */
    public AIMException(short errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Returns a descriptive message of the error that caused this
     * exception.  Overrides the default behavior inherited from the Exception
     * class.
     */
    public String getMessage() {
        String returnVal = "";
        if (this.errorCode == WORD_DEPTH_OVERFLOW) returnVal = WORD_DEPTH_OVERFLOW_DESC; else if (this.errorCode == WORD_BREADTH_OVERFLOW) returnVal = WORD_BREADTH_OVERFLOW_DESC; else if (this.errorCode == FILE_NOT_FOUND) returnVal = FILE_NOT_FOUND_DESC; else if (this.errorCode == CANNOT_READ_FILE) returnVal = CANNOT_READ_FILE_DESC; else if (this.errorCode == INVALID_STOPWORD_FILE) returnVal = INVALID_STOPWORD_FILE_DESC; else if (this.errorCode == CANNOT_READ_STOPWORD_FILE) returnVal = CANNOT_READ_STOPWORD_FILE_DESC; else if (this.errorCode == BAD_COMMAND) returnVal = BAD_COMMAND_DESC; else if (this.errorCode == CANT_WRITE) returnVal = CANT_WRITE_DESC; else if (this.errorCode == NO_FILES) returnVal = NO_FILES_DESC; else if (this.errorCode == STOPFILE_NOT_FOUND) returnVal = STOPFILE_NOT_FOUND_DESC;
        return returnVal;
    }
}
