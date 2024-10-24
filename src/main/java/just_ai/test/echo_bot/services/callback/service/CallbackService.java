package just_ai.test.echo_bot.services.callback.service;

import just_ai.test.echo_bot.services.callback.dto.CallbackDto;

/**
 * Handles requests from vk api
 *
 * @author Roman Mikheev
 */
public interface CallbackService {
    String handleCallback(CallbackDto callbackDto);
}
