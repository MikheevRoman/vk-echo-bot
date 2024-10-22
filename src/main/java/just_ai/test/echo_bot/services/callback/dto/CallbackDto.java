package just_ai.test.echo_bot.services.callback.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import just_ai.test.echo_bot.services.callback.enums.CallbackType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CallbackDto {
    String type;

    Map<String, Object> object;

    String secret;

    @JsonProperty(value = "group_id")
    Long groupId;

    @JsonProperty(value = "event_id")
    String eventId;

    String v;
}
