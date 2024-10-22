package just_ai.test.echo_bot.services.message.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonPropertyOrder(alphabetic = true)
@EqualsAndHashCode(exclude = "randomId")
public class SendMessageRequestDto {
    @JsonProperty(value = "user_id")
    Long userId;

    @JsonProperty(value = "random_id")
    Long randomId;

    @JsonProperty(value = "peer_id")
    Long peerId;

    @JsonProperty(value = "chat_id")
    Long chatId;

    @JsonProperty(value = "group_id")
    Long groupId;

    String message;
}
