/**
 * Exception class used for SECalendar (In alphabetical order)
 * 
 * @author Max Gilman
 * @author Dustin Howett
 * @author Lemuel Lebron
 * @author Gordon Mascarenhas
 * @author PueyWei Tan
 * 
 */
public class SECalendarException extends Exception {

    String message;

    /**
	 * Class constructor that sets this message to parameter's
	 * 
	 * @param message
	 *            String object used to construct superclass Exception object
	 */
    public SECalendarException(String message) {
        this.message = message;
    }

    /**
	 * Return the detailed message of the exception
	 * 
	 * @return The message of the exception
	 */
    public String getMessage() {
        return this.message;
    }
}
