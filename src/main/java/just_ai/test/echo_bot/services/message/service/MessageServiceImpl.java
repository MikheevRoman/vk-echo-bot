package just_ai.test.echo_bot.services.message.service;

import just_ai.test.echo_bot.exception.SendMessageException;
import just_ai.test.echo_bot.services.message.dto.SendMessageRequestDto;
import just_ai.test.echo_bot.services.message.dto.SendMessageResponseDto;
import just_ai.test.echo_bot.services.message.uri.ServiceUri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Objects;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ServiceUri serviceUri;

    @Override
    public void sendMessage(SendMessageRequestDto sendMessageRequestDto) {
        sendMessageRequestDto.setRandomId(0L);
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
}
