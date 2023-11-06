package pl.uwm.wateradventure.models.events;

public enum EventType {

    REJS_WIDOKOWY("Rejs widokowy"),
    PANIENSKI("Wieczór panieński"),
    KAWALERSKI("Wieczór kawalerski"),
    WYNAJEM_SKUTERA("Wynajem skutera wodnego"),
    EVENT_DLA_FIRMY("Event dla firmy");

    public final String enumValue;

    EventType(String enumValue) {
        this.enumValue = enumValue;
    }


}
