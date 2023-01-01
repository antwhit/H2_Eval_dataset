public class KSInternalErrorException extends KinshipSystemException {

    /** Construct an Exception which passes <code>msg</code> up the call stack.    This class handles Kinship System internal Exceptions in general.        @param	msg	a String describing the exact error found.     */
    KSInternalErrorException(String msg) {
        super(msg);
    }
}
