package pl.uwm.wateradventure.exceptions.value_objects_exceptions;

import static pl.uwm.wateradventure.models.events.EventCity.*;

public class InvalidEventCityException extends RuntimeException {

    public static final String MESSAGE =
            String.format("Event city must be one of '%s', '%s', '%s'.",
                    SOPOT, OLECKO, GDANSK);

    public InvalidEventCityException() {
        super(String.format(MESSAGE));
    }

}
