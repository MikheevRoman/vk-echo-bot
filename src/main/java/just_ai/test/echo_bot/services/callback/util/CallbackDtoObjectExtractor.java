package just_ai.test.echo_bot.services.callback.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import just_ai.test.echo_bot.services.callback.dto.CallbackDto;
import just_ai.test.echo_bot.services.callback.dto.MessageCallbackDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CallbackDtoObjectExtractor {
    @Autowired
    ObjectMapper objectMapper;

    public MessageCallbackDto getMessage(CallbackDto callbackDto) {
        Object messageData = callbackDto.getObject().get("message");
        return objectMapper.convertValue(messageData, MessageCallbackDto.class);
    }
}
