package just_ai.test.echo_bot.services.callback.service;

import just_ai.test.echo_bot.config.VkApiProperties;
import just_ai.test.echo_bot.services.callback.dto.CallbackDto;
import just_ai.test.echo_bot.services.callback.dto.MessageCallbackDto;
import just_ai.test.echo_bot.services.callback.util.CallbackDtoObjectExtractor;
import just_ai.test.echo_bot.services.callback.mapper.RequestMapper;
import just_ai.test.echo_bot.services.message.dto.SendMessageRequestDto;
import just_ai.test.echo_bot.services.message.service.MessageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CallbackServiceImpl implements CallbackService {
    @Autowired
    private VkApiProperties vkApiProperties;
    @Autowired
    private MessageServiceImpl messageService;
    @Autowired
    private CallbackDtoObjectExtractor callbackDtoObjectExtractor;

    private final RequestMapper requestMapper = RequestMapper.INSTANCE;

    @Override
    public String handleCallback(CallbackDto callbackDto) {
        switch (callbackDto.getType()) {
            case confirmation -> {
                return vkApiProperties.getConfirmation();
            }
            case message_new -> {
                MessageCallbackDto messageCallbackDto = callbackDtoObjectExtractor.getMessage(callbackDto);
                log.info("new message from [{}], text = {}", messageCallbackDto.getFromId(), messageCallbackDto.getText());
                handleMessageNew(messageCallbackDto);
                return "ok";
            }
            default -> {
                log.warn("unknown callback type: {}", callbackDto.getType());
                throw new UnsupportedOperationException("unsupported callback type");
            }
        }
    }

    private void handleMessageNew(MessageCallbackDto messageCallbackDto) {
        SendMessageRequestDto sendMessageRequestDto = requestMapper.toSendMessageRequestDto(messageCallbackDto);
        messageService.sendMessage(sendMessageRequestDto);
    }
}
