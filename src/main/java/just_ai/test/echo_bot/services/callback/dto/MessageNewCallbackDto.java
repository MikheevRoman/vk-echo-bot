package just_ai.test.echo_bot.services.callback.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageNewCallbackDto {
    Long id;

    @JsonProperty(value = "peer_id")
    Long peerId;

    @JsonProperty(value = "from_id")
    Long fromId;

    @JsonProperty(value = "group_id")
    Long groupId;

    String text;
}
