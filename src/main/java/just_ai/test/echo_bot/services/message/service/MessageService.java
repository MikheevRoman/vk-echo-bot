package just_ai.test.echo_bot.services.message.service;

import just_ai.test.echo_bot.services.message.dto.SendMessageRequestDto;

public interface MessageService {
    void sendMessage(SendMessageRequestDto sendMessageRequestDto);
}
