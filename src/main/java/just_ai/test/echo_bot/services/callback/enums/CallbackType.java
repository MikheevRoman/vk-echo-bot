package just_ai.test.echo_bot.services.callback.enums;

public enum CallbackType {
    message_new,
    confirmation;

    public boolean equals(CallbackType callbackType) {
        return this.toString().equals(callbackType.toString());
    }
}
