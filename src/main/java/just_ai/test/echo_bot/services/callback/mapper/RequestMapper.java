package just_ai.test.echo_bot.services.callback.mapper;

import just_ai.test.echo_bot.services.callback.dto.MessageCallbackDto;
import just_ai.test.echo_bot.services.message.dto.SendMessageRequestDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

/**
 * Converts from callback events to request DTO
 */
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface RequestMapper {
    RequestMapper INSTANCE = Mappers.getMapper(RequestMapper.class);

    /**
     * Adds text "Вы сказали: " to message and maps callback to request DTO
     */
    @Mapping(source = "fromId",target = "userId")
    @Mapping(source = "text", target = "message", qualifiedByName = "updateText")
    SendMessageRequestDto toSendMessageRequestDto(MessageCallbackDto messageCallbackDto);

    @Named("updateText")
    default String updateText(String text) {
        return "Вы сказали: " + text;
    }
}
