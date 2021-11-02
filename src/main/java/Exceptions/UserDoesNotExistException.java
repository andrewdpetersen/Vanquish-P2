package Exceptions;

/**
 * Custom Runtime Exception
 *
 * @date
 * @author
 */
public class UserDoesNotExistException extends RuntimeException {
    public UserDoesNotExistException() {
        super();
    }

    public UserDoesNotExistException(String message) {
        super(message);
    }
}
