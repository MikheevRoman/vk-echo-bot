package just_ai.test.echo_bot.services.callback.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Incoming message DTO
 *
 * @see <a href=https://dev.vk.com/ru/reference/objects/message>Message struct documentation</a>
 */
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageCallbackDto {
    private Long id;

    @JsonProperty(value = "peer_id")
    private Long peerId;

    @JsonProperty(value = "from_id")
    private Long fromId;

    private String text;
}
