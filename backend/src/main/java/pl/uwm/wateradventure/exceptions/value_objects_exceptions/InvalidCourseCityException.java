package pl.uwm.wateradventure.exceptions.value_objects_exceptions;

import static pl.uwm.wateradventure.models.courses.CourseCity.OLECKO;
import static pl.uwm.wateradventure.models.courses.CourseCity.SOPOT;

public class InvalidCourseCityException extends RuntimeException {

    public static final String MESSAGE =
            String.format("Event city must be one of '%s', '%s'.",
                    SOPOT, OLECKO);

    public InvalidCourseCityException() {
        super(String.format(MESSAGE));
    }
}
