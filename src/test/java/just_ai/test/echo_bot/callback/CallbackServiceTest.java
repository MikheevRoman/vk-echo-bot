package just_ai.test.echo_bot.callback;

import just_ai.test.echo_bot.callback.dto.CallbackDto;
import just_ai.test.echo_bot.callback.dto.MessageCallbackDto;
import just_ai.test.echo_bot.callback.enums.CallbackType;
import just_ai.test.echo_bot.callback.mapper.RequestMapper;
import just_ai.test.echo_bot.callback.service.CallbackServiceImpl;
import just_ai.test.echo_bot.callback.util.CallbackDtoObjectExtractor;
import just_ai.test.echo_bot.config.VkApiProperties;
import just_ai.test.echo_bot.message.dto.SendMessageRequestDto;
import just_ai.test.echo_bot.message.service.MessageServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CallbackServiceTest {
    @Mock
    private VkApiProperties vkApiProperties;
    @Mock
    private MessageServiceImpl messageService;
    @Mock
    private CallbackDtoObjectExtractor callbackDtoObjectExtractor;

    @InjectMocks
    private CallbackServiceImpl callbackService;

    @Test
    void handleCallback_confirmationType_shouldReturnConfirmationString() {
        CallbackDto callbackDto = new CallbackDto();
        callbackDto.setType(CallbackType.confirmation);

        String expectedConfirmation = "some-confirmation-string";
        when(vkApiProperties.getConfirmation()).thenReturn(expectedConfirmation);

        String result = callbackService.handleCallback(callbackDto);

        assertEquals(expectedConfirmation, result);
        verify(vkApiProperties).getConfirmation();
        verifyNoInteractions(messageService, callbackDtoObjectExtractor);
    }

    @Test
    void handleCallback_messageNewType_shouldProcessNewMessage() {
        CallbackDto callbackDto = new CallbackDto();
        callbackDto.setType(CallbackType.message_new);

        MessageCallbackDto messageCallbackDto = new MessageCallbackDto();
        messageCallbackDto.setFromId(12345L);
        messageCallbackDto.setText("Hello!");

        RequestMapper requestMapper = RequestMapper.INSTANCE;
        SendMessageRequestDto sendMessageRequestDto = requestMapper.toSendMessageRequestDto(messageCallbackDto);

        when(callbackDtoObjectExtractor.getMessage(callbackDto)).thenReturn(messageCallbackDto);

        String result = callbackService.handleCallback(callbackDto);

        assertEquals("ok", result);
        verify(callbackDtoObjectExtractor).getMessage(callbackDto);
        verify(messageService).sendMessage(sendMessageRequestDto);
    }
}
