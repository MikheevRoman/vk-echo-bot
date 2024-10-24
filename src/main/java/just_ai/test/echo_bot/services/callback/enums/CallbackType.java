package just_ai.test.echo_bot.services.callback.enums;

/**
 * VK API callback type describes event such as incoming message
 *
 * @see <a href=https://dev.vk.com/ru/api/community-events/json-schema#message_new>All event list</a>
 */
public enum CallbackType {
    message_new,
    confirmation;
}
