package just_ai.test.echo_bot.callback.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import just_ai.test.echo_bot.callback.dto.MessageCallbackDto;
import just_ai.test.echo_bot.callback.dto.CallbackDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Gets the required objects from the {@code object} {@link CallbackDto} field
 */
@Component
public class CallbackDtoObjectExtractor {
    @Autowired
    ObjectMapper objectMapper;

    public MessageCallbackDto getMessage(CallbackDto callbackDto) {
        Object messageData = callbackDto.getObject().get("message");
        return objectMapper.convertValue(messageData, MessageCallbackDto.class);
    }
}
