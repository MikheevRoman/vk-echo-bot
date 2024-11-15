package just_ai.test.echo_bot.callback.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import just_ai.test.echo_bot.callback.enums.CallbackType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;

/**
 * VK API callback request DTO
 *
 * @see <a href=https://dev.vk.com/ru/api/callback/getting-started>VK API documentation</a>
 * @author Mikheev Roman
 */
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CallbackDto {
    CallbackType type;

    /**
     * the object that initiated the event, such as a {@link MessageCallbackDto}
     * @see <a href=https://dev.vk.com/ru/api/community-events/json-schema#message_new>other event</a>
     */
    Map<String, Object> object;

    @JsonProperty(value = "group_id")
    Long groupId;

    @JsonProperty(value = "event_id")
    String eventId;

    /**
     * API version
     */
    String v;
}
