package just_ai.test.echo_bot.message.service;

import just_ai.test.echo_bot.message.dto.SendMessageRequestDto;

/**
 * Service that allows to work with messages
 *
 * @see <a href=https://dev.vk.com/ru/method/messages>supported message methods documentation</a>
 */
public interface MessageService {
    void sendMessage(SendMessageRequestDto sendMessageRequestDto);
}
