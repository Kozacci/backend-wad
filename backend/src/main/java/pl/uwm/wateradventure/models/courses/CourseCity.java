package pl.uwm.wateradventure.models.courses;

import pl.uwm.wateradventure.exceptions.value_objects_exceptions.InvalidCourseCityException;

import java.util.stream.Stream;

public enum CourseCity {

    SOPOT("Sopot"),
    OLECKO("Olecko");

    public final String enumValue;

    CourseCity(String enumValue) {
        this.enumValue = enumValue;
    }

    public static CourseCity getCourseCity(String givenValue) {
        return Stream
                .of(values())
                .filter(courseCity -> courseCity.enumValue.equals(givenValue))
                .findFirst()
                .orElseThrow(InvalidCourseCityException::new);
    }

}
