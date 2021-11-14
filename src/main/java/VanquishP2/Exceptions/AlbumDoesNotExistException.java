package VanquishP2.Exceptions;

/**
 * Custom Runtime Exception
 *
 * @date 11/1/2021
 * @author Kollier Martin
 */
public class AlbumDoesNotExistException extends RuntimeException{
    public AlbumDoesNotExistException() {
        super();
    }

    public AlbumDoesNotExistException(String message) {
        super(message);
    }
}
