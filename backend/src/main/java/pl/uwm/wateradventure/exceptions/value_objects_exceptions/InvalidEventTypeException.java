package pl.uwm.wateradventure.exceptions.value_objects_exceptions;

import static pl.uwm.wateradventure.models.events.EventType.*;

public class InvalidEventTypeException extends RuntimeException {

    public static final String MESSAGE =
            String.format("Event type must be one of '%s', '%s', '%s', '%s', '%s'.",
                    PANIENSKI.enumValue, KAWALERSKI.enumValue, REJS_WIDOKOWY.enumValue, WYNAJEM_SKUTERA.enumValue, EVENT_DLA_FIRMY.enumValue);

    public InvalidEventTypeException() {
        super(String.format(MESSAGE));
    }

}
