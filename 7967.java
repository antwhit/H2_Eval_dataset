/**
 * Thrown when an account cannot be located in meta database.
 */
public class AccountNotFoundException extends Exception {

    /**
     * Creates a new instance of AccountNotFoundException.
     */
    public AccountNotFoundException() {
        super();
    }

    /**
     * Creates a new instance of AccountNotFoundException.
     * @param message A custom error message.
     */
    public AccountNotFoundException(String message) {
        super(message);
    }
}
