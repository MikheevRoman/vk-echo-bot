package just_ai.test.echo_bot.message.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * VK API request DTO to send message
 *
 * @see <a href=https://dev.vk.com/ru/method/messages.send#%D0%9F%D1%80%D0%B8%D0%BC%D0%B5%D1%80%20%D0%B7%D0%B0%D0%BF%D1%80%D0%BE%D1%81%D0%B0>send message documentation</a>
 */
@Builder
@Getter @Setter
@JsonPropertyOrder(alphabetic = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(exclude = "randomId")
public class SendMessageRequestDto {
    /**
     * Mandatory parameter. The ID of the user to whom the message is sent. You can use peer_id instead.
     */
    @JsonProperty(value = "user_id")
    Long userId;

    /**
     * Mandatory parameter. Unique identifier designed to prevent the same message from being sent twice. Possible values:<br>
     * <i>0</i> — uniqueness check is not needed.<br>
     * <i>Any other number within int32</i> — uniqueness check is needed.
     */
    @JsonProperty(value = "random_id")
    Long randomId;

    /**
     * Optional parameter. The identifier of the message recipient:<br>
     * For a user — <i>USER_ID</i><br>
     * For a group conversation — <i>2000000000 + CONVERSATION_ID</i><br>
     * For a community — <i>COMMUNITY_ID</i>
     */
    @JsonProperty(value = "peer_id")
    Long peerId;

    /**
     * Optional parameter. Message text
     */
    String message;
}
