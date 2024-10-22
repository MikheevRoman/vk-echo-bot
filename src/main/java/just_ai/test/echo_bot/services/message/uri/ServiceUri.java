package just_ai.test.echo_bot.services.message.uri;

import com.fasterxml.jackson.databind.ObjectMapper;
import just_ai.test.echo_bot.config.VkApiProperties;
import just_ai.test.echo_bot.services.message.dto.SendMessageRequestDto;
import just_ai.test.echo_bot.exception.SendMessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class ServiceUri {
    @Autowired
    private VkApiProperties vkApiProperties;
    @Autowired
    private ObjectMapper paramsMapper;

    private final String VK_API_URL = "https://api.vk.com/method";

    public URI sendMessageUri(SendMessageRequestDto dto) {
        try {
            LinkedMultiValueMap<String, String> map = paramsMapper.convertValue(dto, LinkedMultiValueMap.class);
            return UriComponentsBuilder.fromHttpUrl(VK_API_URL + "/messages.send")
                    .queryParam("access_token", vkApiProperties.getToken())
                    .queryParam("v", vkApiProperties.getV())
                    .queryParams(map)
                    .build()
                    .toUri();
        } catch (ClassCastException e) {
            throw new SendMessageException(e);
        }
    }
}
