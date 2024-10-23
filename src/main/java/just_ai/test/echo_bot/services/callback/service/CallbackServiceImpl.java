package just_ai.test.echo_bot.services.callback.service;

import just_ai.test.echo_bot.config.VkApiProperties;
import just_ai.test.echo_bot.services.callback.dto.CallbackDto;
import just_ai.test.echo_bot.services.callback.dto.MessageNewCallbackDto;
import just_ai.test.echo_bot.services.message.dto.SendMessageRequestDto;
import just_ai.test.echo_bot.services.message.service.MessageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class CallbackServiceImpl implements CallbackService {
    @Autowired
    private VkApiProperties vkApiProperties;

    @Autowired
    private MessageServiceImpl messageService;

    @Override
    public String handleCallback(CallbackDto callbackDto) {
        switch (callbackDto.getType()) {
            case confirmation -> {
                return vkApiProperties.getConfirmation();
            }
            case message_new -> {
                MessageNewCallbackDto messageNewCallbackDto = parseMessageNewDto(callbackDto);
                log.info("new message from [{}], text = {}", messageNewCallbackDto.getFromId(), messageNewCallbackDto.getText());
                handleMessageNew(messageNewCallbackDto);
                return "ok";
            }
            default -> {
                log.warn("unknown callback type: {}", callbackDto.getType());
                throw new UnsupportedOperationException("unsupported callback type");
            }
        }
    }

    private void handleMessageNew(MessageNewCallbackDto messageNewCallbackDto) {
        SendMessageRequestDto sendMessageRequestDto = SendMessageRequestDto.builder()
                .userId(messageNewCallbackDto.getFromId())
                .peerId(messageNewCallbackDto.getPeerId())
                .message("Вы сказали: " + messageNewCallbackDto.getText())
                .groupId(messageNewCallbackDto.getGroupId())
                .build();
        messageService.sendMessage(sendMessageRequestDto);
    }

    private MessageNewCallbackDto parseMessageNewDto(CallbackDto callbackDto) {
        Map<String, Object> message = (Map<String, Object>) callbackDto.getObject().get("message");
        return MessageNewCallbackDto.builder()
                .id(Long.parseLong(String.valueOf(message.get("id"))))
                .peerId(Long.parseLong(String.valueOf(message.get("peer_id"))))
                .fromId(Long.parseLong(String.valueOf(message.get("from_id"))))
                .text(String.valueOf(message.get("text")))
                .groupId(callbackDto.getGroupId())
                .build();
    }
}
