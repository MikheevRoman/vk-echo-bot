package just_ai.test.echo_bot.callback.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Request exception handler<br><br>
 * All response must contain "ok"
 */
@ControllerAdvice
public class CallbackResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({RuntimeException.class})
    protected ResponseEntity<Object> handleOk(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, "ok", null, HttpStatus.OK, request);
    }
}
