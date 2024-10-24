package just_ai.test.echo_bot.services.callback.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * Incoming message DTO
 *
 * @see <a href=https://dev.vk.com/ru/reference/objects/message>Message struct documentation</a>
 */
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageCallbackDto {
    Long id;

    @JsonProperty(value = "peer_id")
    Long peerId;

    @JsonProperty(value = "from_id")
    Long fromId;

    String text;
}
