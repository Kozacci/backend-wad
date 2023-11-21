package pl.uwm.wateradventure.models.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pl.uwm.wateradventure.models.courses.CourseType;
import java.util.Arrays;

public class CheckCourseTypeValidator implements ConstraintValidator<pl.uwm.wateradventure.models.validators.CourseType, String> {

    @Override
    public boolean isValid(String givenValue, ConstraintValidatorContext constraintValidatorContext) {
        return Arrays.stream(CourseType.values())
                .anyMatch(courseType -> courseType.enumValue.equals(givenValue));
    }

}
