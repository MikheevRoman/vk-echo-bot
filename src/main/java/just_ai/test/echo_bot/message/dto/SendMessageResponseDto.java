package just_ai.test.echo_bot.message.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * Response DTO of {@link SendMessageRequestDto}
 *
 * @see <a href=https://dev.vk.com/ru/method/messages.send#%D0%A0%D0%B5%D0%B7%D1%83%D0%BB%D1%8C%D1%82%D0%B0%D1%82>response structure documentation</a>
 */
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendMessageResponseDto {
    @JsonProperty(value = "peer_id")
    Long peerId;

    @JsonProperty(value = "message_id")
    Long messageId;

    @JsonProperty(value = "conversation_message_id")
    Long conversationMessageId;

    String error;
}
