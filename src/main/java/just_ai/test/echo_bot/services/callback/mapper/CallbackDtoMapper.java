package just_ai.test.echo_bot.services.callback.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import just_ai.test.echo_bot.services.callback.dto.CallbackDto;
import just_ai.test.echo_bot.services.callback.dto.MessageNewCallbackDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CallbackDtoMapper {
    @Autowired
    ObjectMapper objectMapper;

    public MessageNewCallbackDto getMessage(CallbackDto callbackDto) {
        Object messageData = callbackDto.getObject().get("message");
        return objectMapper.convertValue(messageData, MessageNewCallbackDto.class);
    }
}
