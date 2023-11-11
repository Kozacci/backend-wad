package pl.uwm.wateradventure.exceptions.value_objects_exceptions;

import static pl.uwm.wateradventure.models.courses.CourseStatus.*;

public class InvalidCourseStatusException extends RuntimeException {

    public static final String MESSAGE =
            String.format("Course status must be one of '%s', '%s', '%s'.",
                    ROZPOCZETY, NIEROZPOCZETY, ZAKONCZONY);

    public InvalidCourseStatusException() {
            super(String.format(MESSAGE));
        }

}
