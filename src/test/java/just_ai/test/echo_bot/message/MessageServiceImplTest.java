package just_ai.test.echo_bot.message;

import just_ai.test.echo_bot.exception.SendMessageException;
import just_ai.test.echo_bot.message.dto.SendMessageRequestDto;
import just_ai.test.echo_bot.message.dto.SendMessageResponseDto;
import just_ai.test.echo_bot.message.service.MessageServiceImpl;
import just_ai.test.echo_bot.message.uri.ServiceUri;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageServiceImplTest {

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ServiceUri serviceUri;

    @InjectMocks
    private MessageServiceImpl messageService;

    private SendMessageRequestDto requestDto;

    @BeforeEach
    void setUp() {
        requestDto = SendMessageRequestDto.builder()
                .userId(12345L)
                .message("Hello!")
                .build();
    }

    @Test
    void sendMessage_shouldSendRequestSuccessfully() {
        URI uri = URI.create("https://api.vk.com/method/sendMessage");
        SendMessageResponseDto responseDto = new SendMessageResponseDto();
        responseDto.setError(null);
        ResponseEntity<SendMessageResponseDto> responseEntity = new ResponseEntity<>(responseDto, HttpStatus.OK);

        when(serviceUri.sendMessageUri(requestDto)).thenReturn(uri);
        when(restTemplate.postForEntity(uri, null, SendMessageResponseDto.class)).thenReturn(responseEntity);

        messageService.sendMessage(requestDto);

        verify(restTemplate).postForEntity(uri, null, SendMessageResponseDto.class);
    }

    @Test
    void sendMessage_shouldThrowExceptionWhenResponseHasError() {
        URI uri = URI.create("https://api.vk.com/method/sendMessage");
        SendMessageResponseDto responseDto = new SendMessageResponseDto();
        responseDto.setError("Some error");
        ResponseEntity<SendMessageResponseDto> responseEntity = new ResponseEntity<>(responseDto, HttpStatus.OK);

        when(serviceUri.sendMessageUri(requestDto)).thenReturn(uri);
        when(restTemplate.postForEntity(uri, null, SendMessageResponseDto.class)).thenReturn(responseEntity);

        assertThrows(SendMessageException.class, () -> messageService.sendMessage(requestDto));
    }

    @Test
    void recover_shouldLogErrorWhenRetriesFail() {
        SendMessageException exception = new SendMessageException("Final retry failed");

        messageService.recover(exception, requestDto);
    }
}

