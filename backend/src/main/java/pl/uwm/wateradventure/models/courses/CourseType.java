package pl.uwm.wateradventure.models.courses;

import pl.uwm.wateradventure.exceptions.value_objects_exceptions.InvalidCourseTypeException;

import java.util.stream.Stream;

public enum CourseType {

    STERNIK_MOTOROWODNY("Sternik motorowodny"),
    MOTOROWODNY_STERNIK_MORSKI("Motorowodny sternik morski"),
    HOLOWANIE_NARCIARZA_I_OBIEKTOW_NAWODNYCH("Holowanie narciarza i obiektow nawodnych"),
    ZEGLARZ_JACHTOWY("Żeglarz jachtowy"),
    JACHTOWY_STERNIK_MORSKI("Jachtowy sternik morski");

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
