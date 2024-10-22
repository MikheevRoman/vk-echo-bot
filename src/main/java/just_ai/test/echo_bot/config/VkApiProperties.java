package just_ai.test.echo_bot.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "vk.api")
@PropertySource(value = "classpath:vk_api.properties")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
public class VkApiProperties {
    String token;
    String version;
    String secret;
    String confirmation;
}