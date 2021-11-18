package Application.exceptions;

/**
 * Custom Runtime Exception
 *
 * @date 11/1/2021
 * @author Vanquish
 */
public class DuplicateEntryException extends RuntimeException {
    public DuplicateEntryException() {
        super();
    }

    public DuplicateEntryException(String message) {
        super(message);
    }
}
