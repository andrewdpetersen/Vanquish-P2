package VanquishP2.Exceptions;

/**
 * Custom Runtime Exception
 *
 * @date 11/1/2021
 * @author Kollier Martin
 */
public class AuthenticationException extends RuntimeException {
    public AuthenticationException() {
        super();
    }

    public AuthenticationException(String message) {
        super(message);
    }
}
