package just_ai.test.echo_bot.services.callback.service;

import just_ai.test.echo_bot.services.callback.dto.CallbackDto;

public interface CallbackService {
    String handleCallback(CallbackDto callbackDto);
}
