package just_ai.test.echo_bot.callback.mapper;

import javax.annotation.processing.Generated;
import just_ai.test.echo_bot.callback.dto.MessageCallbackDto;
import just_ai.test.echo_bot.message.dto.SendMessageRequestDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-13T23:52:57+0300",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class RequestMapperImpl implements RequestMapper {

    @Override
    public SendMessageRequestDto toSendMessageRequestDto(MessageCallbackDto messageCallbackDto) {
        if ( messageCallbackDto == null ) {
            return null;
        }

        SendMessageRequestDto.SendMessageRequestDtoBuilder sendMessageRequestDto = SendMessageRequestDto.builder();

        sendMessageRequestDto.userId( messageCallbackDto.getFromId() );
        sendMessageRequestDto.randomId( getRandomId( messageCallbackDto ) );
        sendMessageRequestDto.message( updateText( messageCallbackDto.getText() ) );
        sendMessageRequestDto.peerId( messageCallbackDto.getPeerId() );

        return sendMessageRequestDto.build();
    }
}
