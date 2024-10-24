package just_ai.test.echo_bot.services.message.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter @Setter
@JsonPropertyOrder(alphabetic = true)
@EqualsAndHashCode(exclude = "randomId")
public class SendMessageRequestDto {
    @JsonProperty(value = "user_id")
    private Long userId;

    @JsonProperty(value = "random_id")
    private Long randomId;

    @JsonProperty(value = "peer_id")
    private Long peerId;

    private String message;
}
