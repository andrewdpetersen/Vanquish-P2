package VanquishP2.Exceptions;

/**
 * Custom Runtime Exception
 *
 * @date 11/1/2021
 * @author Kollier Martin
 */
public class UserDoesNotExistException extends RuntimeException {
    public UserDoesNotExistException() {
        super();
    }

    public UserDoesNotExistException(String message) {
        super(message);
    }
}
