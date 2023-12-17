package pl.uwm.wateradventure.models.courses;

import pl.uwm.wateradventure.exceptions.value_objects_exceptions.InvalidCourseTypeException;

import java.util.stream.Stream;

public enum CourseType {

    STERNIK_MOTOROWODNY("Sternik motorowodny"),
    JACHTOWY_STERNIK_MORSKI("Jachtowy sternik morski"),
    MOTOROWODNY_STERNIK_MORSKI("Motorowodny sternik morski"),
    ZEGLARZ_JACHTOWY("Żeglarz jachtowy"),
    WARSZTATY_NAWIGACYJNE("Warsztaty nawigacyjne"),
    REJSY_STAZOWE("Rejsy stażowe");

    public final String enumValue;

    CourseType(String enumValue) {
        this.enumValue = enumValue;
    }

    public static CourseType getCourseType(String givenValue) {
        return Stream
                .of(values())
                .filter(courseType -> courseType.enumValue.equals(givenValue))
                .findFirst()
                .orElseThrow(InvalidCourseTypeException::new);
    }

}
