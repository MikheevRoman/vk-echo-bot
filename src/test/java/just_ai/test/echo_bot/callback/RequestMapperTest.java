package just_ai.test.echo_bot.callback;

import just_ai.test.echo_bot.callback.dto.MessageCallbackDto;
import just_ai.test.echo_bot.callback.mapper.RequestMapper;
import just_ai.test.echo_bot.message.dto.SendMessageRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class RequestMapperTest {
    private RequestMapper requestMapper;

    @BeforeEach
    void setUp() {
        requestMapper = Mappers.getMapper(RequestMapper.class);
    }

    @Test
    void testToSendMessageRequestDto() {
        MessageCallbackDto messageCallbackDto = new MessageCallbackDto();
        messageCallbackDto.setFromId(123L);
        messageCallbackDto.setText("Hello, world!");

        SendMessageRequestDto result = requestMapper.toSendMessageRequestDto(messageCallbackDto);

        assertNotNull(result);
        assertEquals(result.getUserId(), 123L);
        assertEquals(result.getMessage(), "Вы сказали: Hello, world!");
        assertEquals(result.getRandomId(), messageCallbackDto.hashCode());
    }

    @Test
    void testUpdateText() {
        String originalText = "Hello, world!";

        String updatedText = requestMapper.updateText(originalText);

        assertEquals(updatedText, "Вы сказали: Hello, world!");
    }

    @Test
    void testGetRandomId() {
        MessageCallbackDto messageCallbackDto = new MessageCallbackDto();
        messageCallbackDto.setText("Hello, world!");

        Long randomId = requestMapper.getRandomId(messageCallbackDto);

        assertEquals(randomId, messageCallbackDto.hashCode());
    }
}
