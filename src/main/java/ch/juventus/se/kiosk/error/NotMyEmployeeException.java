package ch.juventus.se.kiosk.error;

/**
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class NotMyEmployeeException extends Exception {

    public NotMyEmployeeException(String message) {
        super(message);
    }
}
