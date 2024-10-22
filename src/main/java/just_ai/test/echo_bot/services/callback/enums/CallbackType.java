package just_ai.test.echo_bot.services.callback.enums;

public enum CallbackType {
    MESSAGE_NEW,
    CONFIRMATION;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }

    public boolean equals(CallbackType callbackType) {
        return this.toString().equals(callbackType.toString());
    }
}
