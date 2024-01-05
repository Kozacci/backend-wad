package pl.uwm.wateradventure.exceptions.value_objects_exceptions;

import static pl.uwm.wateradventure.models.courses.CourseType.*;

public class InvalidCourseTypeException extends RuntimeException {

    public static final String MESSAGE =
            String.format("Course type must be one of '%s', '%s'," +
                            "'%s', '%s', '%s', '%s'.",
                    STERNIK_MOTOROWODNY.enumValue,
                    JACHTOWY_STERNIK_MORSKI.enumValue,
                    MOTOROWODNY_STERNIK_MORSKI.enumValue,
                    ZEGLARZ_JACHTOWY.enumValue,
                    WARSZTATY_NAWIGACYJNE.enumValue,
                    REJSY_STAZOWE.enumValue
            );

    public InvalidCourseTypeException() {
        super(String.format(MESSAGE));
    }
}
