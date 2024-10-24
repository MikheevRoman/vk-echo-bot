package just_ai.test.echo_bot.services.callback.enums;

/**
 * VK API callback type describes event such as incoming message
 * @see <a href=https://dev.vk.com/ru/api/community-events/json-schema#message_new>All event list</a>
 */
public enum CallbackType {
    /**
     * Incoming message
     */
    message_new,

    /**
     * Server confirmation event
     * @see <a href=https://dev.vk.com/ru/api/callback/getting-started#%D0%A0%D0%B0%D0%B1%D0%BE%D1%82%D0%B0%20%D1%81%20Callback%20API>server registration</a>
     */
    confirmation;
}
