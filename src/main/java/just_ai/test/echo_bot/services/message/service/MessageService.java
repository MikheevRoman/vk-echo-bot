package just_ai.test.echo_bot.services.message.service;

import just_ai.test.echo_bot.services.message.dto.SendMessageRequestDto;

/**
 * Service that allows to work with messages
 *
 * @see <a href=https://dev.vk.com/ru/method/messages>supported message methods documentation</a>
 */
public interface MessageService {
    void sendMessage(SendMessageRequestDto sendMessageRequestDto);
}
