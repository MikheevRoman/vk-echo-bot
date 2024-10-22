package just_ai.test.echo_bot.services.message.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
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
