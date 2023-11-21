package pl.uwm.wateradventure.models.courses;

import pl.uwm.wateradventure.exceptions.value_objects_exceptions.InvalidCourseStatusException;

import java.util.stream.Stream;

public enum CourseStatus {

    NIEROZPOCZETY("Nierozpoczęty"),
    ROZPOCZETY("Rozpoczęty"),
    ZAKONCZONY("Zakończony");

    public final String enumValue;

    CourseStatus(String enumValue) {
        this.enumValue = enumValue;
    }

    public static CourseStatus getCourseStatus(String givenValue) {
        return Stream
                .of(values())
                .filter(courseStatus -> courseStatus.enumValue.equals(givenValue))
                .findFirst()
                .orElseThrow(InvalidCourseStatusException::new);
    }

}
