package just_ai.test.echo_bot.services.callback.service;

import just_ai.test.echo_bot.config.VkApiProperties;
import just_ai.test.echo_bot.services.callback.dto.CallbackDto;
import just_ai.test.echo_bot.services.callback.dto.MessageNewCallbackDto;
import just_ai.test.echo_bot.services.message.dto.SendMessageRequestDto;
import just_ai.test.echo_bot.services.message.service.MessageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
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
        checkSecretParameter(callbackDto);
        switch (callbackDto.getType()) {
            case "confirmation" -> {
                log.info(vkApiProperties.getConfirmation());
                return vkApiProperties.getConfirmation();
            }
            case "message_new" -> {
                MessageNewCallbackDto messageNewCallbackDto = parseMessageNewDto(callbackDto);
                handleMessageNew(messageNewCallbackDto);
                return "ok";
            }
            default -> {
                log.info("unknown callback type: {}", callbackDto.getType());
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

    private static MessageNewCallbackDto parseMessageNewDto(CallbackDto callbackDto) {
        Map<String, Object> map = callbackDto.getObject();
        return MessageNewCallbackDto.builder()
                .id(Long.parseLong(String.valueOf(map.get("id"))))
                .peerId(Long.parseLong(String.valueOf(map.get("peer_id"))))
                .fromId(Long.parseLong(String.valueOf(map.get("from_id"))))
                .text(String.valueOf(map.get("text")))
                .groupId(callbackDto.getGroupId())
                .build();
    }

    private void checkSecretParameter(CallbackDto callbackDto) {
        if (!vkApiProperties.getSecret().equals(callbackDto.getSecret())) {
            throw new InvalidParameterException();
        }
    }
}
