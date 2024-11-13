package just_ai.test.echo_bot.message.service;

import just_ai.test.echo_bot.exception.SendMessageException;
import just_ai.test.echo_bot.message.dto.SendMessageRequestDto;
import just_ai.test.echo_bot.message.dto.SendMessageResponseDto;
import just_ai.test.echo_bot.message.uri.ServiceUri;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Objects;

/**
 * This implementation supports VK API <b>messages.send</b> method
 *
 * @see <a href=https://dev.vk.com/ru/method/messages>supported message methods documentation</a>
 */
@Service
@Slf4j
public class MessageServiceImpl implements MessageService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ServiceUri serviceUri;

    @Override
    @Retryable(
            retryFor = {SendMessageException.class, HttpStatusCodeException.class},
            maxAttempts = 10,
            backoff = @Backoff(multiplier = 1.5)
    )
    public void sendMessage(SendMessageRequestDto sendMessageRequestDto) {
        URI uri = serviceUri.sendMessageUri(sendMessageRequestDto);
        ResponseEntity<SendMessageResponseDto> responseEntity = restTemplate.postForEntity(
                uri,
                null,
                SendMessageResponseDto.class
        );
        handleResponse(responseEntity);
    }

    private void handleResponse(ResponseEntity<SendMessageResponseDto> response) {
        String error = Objects.requireNonNull(response.getBody()).getError();
        if (error != null && !error.isEmpty()) {
            throw new SendMessageException(error);
        }
    }

    @Recover
    public void recover(SendMessageException e, SendMessageRequestDto sendMessageRequestDto) {
        log.error("cannot send message to user [userId={}], error: {}", sendMessageRequestDto.getUserId(), e.getMessage());
    }
}
