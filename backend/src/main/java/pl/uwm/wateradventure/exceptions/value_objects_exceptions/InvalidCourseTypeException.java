package pl.uwm.wateradventure.exceptions.value_objects_exceptions;

import static pl.uwm.wateradventure.models.courses.CourseType.*;

public class InvalidCourseTypeException extends RuntimeException {

    public static final String MESSAGE =
            String.format("Course type must be one of '%s', '%s'," +
                            "'%s', '%s', '%s'.",
                    STERNIK_MOTOROWODNY, MOTOROWODNY_STERNIK_MORSKI,
                    HOLOWANIE_NARCIARZA_I_OBIEKTOW_NAWODNYCH, ZEGLARZ_JACHTOWY, JACHTOWY_STERNIK_MORSKI);

    public InvalidCourseTypeException() {
        super(String.format(MESSAGE));
    }
}
