package just_ai.test.echo_bot.callback.controller;

import just_ai.test.echo_bot.callback.dto.CallbackDto;
import just_ai.test.echo_bot.callback.service.CallbackServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Vk api requests controller
 *
 * @author Roman Mikheev
 */
@RestController
@RequestMapping(value = "/bot/callback")
public class CallbackController {
    @Autowired
    private CallbackServiceImpl callbackService;

    @PostMapping
    public ResponseEntity<String> processCallback(@RequestBody CallbackDto callbackDto) {
        return new ResponseEntity<>(callbackService.handleCallback(callbackDto), HttpStatus.OK);
    }
}
