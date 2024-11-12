package just_ai.test.echo_bot.exception;

/**
 * Exception thrown when sending a message fails
 *
 * @author Roman Mikheev
 */
public class SendMessageException extends RuntimeException {
    public SendMessageException(String message) {
        super(message);
    }

    public SendMessageException(Throwable cause) {
        super(cause);
    }
}
