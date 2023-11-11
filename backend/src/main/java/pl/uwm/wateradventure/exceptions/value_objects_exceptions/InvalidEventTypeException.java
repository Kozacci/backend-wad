package pl.uwm.wateradventure.exceptions.value_objects_exceptions;

import static pl.uwm.wateradventure.models.events.EventType.*;

public class InvalidEventTypeException extends RuntimeException {

    public static final String MESSAGE =
            String.format("Event type must be one of '%s', '%s', '%s', '%s', '%s'.",
                    PANIENSKI, KAWALERSKI, REJS_WIDOKOWY, WYNAJEM_SKUTERA, EVENT_DLA_FIRMY);

    public InvalidEventTypeException() {
        super(String.format(MESSAGE));
    }

}
