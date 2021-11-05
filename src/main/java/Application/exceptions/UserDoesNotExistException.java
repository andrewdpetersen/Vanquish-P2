package Application.exceptions;

/**
 * Custom Runtime Exception
 *
 * @date 11/1/2021
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