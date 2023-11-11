package pl.uwm.wateradventure.models.events;

import pl.uwm.wateradventure.exceptions.value_objects_exceptions.InvalidEventTypeException;

import java.util.stream.Stream;

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

    public static EventType getEventType(String givenValue) {
        return Stream
                .of(values())
                .filter(eventType -> eventType.enumValue.equals(givenValue))
                .findFirst()
                .orElseThrow(InvalidEventTypeException::new);
    }

}
