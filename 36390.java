/**
 * Thrown when a database cannot be located in meta database.
 */
public class DatabaseNotFoundException extends Exception {

    /**
     * Creates a new instance of DatabaseNotFoundException.
     */
    public DatabaseNotFoundException() {
        super();
    }

    /**
     * Creates a new instance of DatabaseNotFoundException.
     * @param message A custom error message.
     */
    public DatabaseNotFoundException(String message) {
        super(message);
    }
}
