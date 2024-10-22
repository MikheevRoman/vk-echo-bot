package just_ai.test.echo_bot.exception;

public class SendMessageException extends RuntimeException {
    public SendMessageException(String message) {
        super(message);
    }

    public SendMessageException(Throwable cause) {
        super(cause);
    }

    public SendMessageException(String message, Throwable cause) {
        super(message, cause);
    }
}
