package just_ai.test.echo_bot.callback;

import com.fasterxml.jackson.databind.ObjectMapper;
import just_ai.test.echo_bot.callback.controller.CallbackController;
import just_ai.test.echo_bot.callback.dto.CallbackDto;
import just_ai.test.echo_bot.callback.enums.CallbackType;
import just_ai.test.echo_bot.callback.service.CallbackServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CallbackController.class)
public class CallbackControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CallbackServiceImpl callbackService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void processCallback_shouldReturnOkResponse() throws Exception {
        CallbackDto callbackDto = new CallbackDto();
        callbackDto.setType(CallbackType.confirmation);

        String expectedResponse = "";
        when(callbackService.handleCallback(callbackDto)).thenReturn(expectedResponse);

        mockMvc.perform(post("/bot/callback")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(callbackDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }

    @Test
    void processCallback_shouldReturnOkResponseForMessageNew() throws Exception {
        CallbackDto callbackDto = new CallbackDto();
        callbackDto.setType(CallbackType.message_new);

        String expectedResponse = "";
        when(callbackService.handleCallback(callbackDto)).thenReturn(expectedResponse);

        mockMvc.perform(post("/bot/callback")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(callbackDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }
}
