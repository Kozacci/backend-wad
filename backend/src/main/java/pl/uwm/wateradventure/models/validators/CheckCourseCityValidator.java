package pl.uwm.wateradventure.models.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pl.uwm.wateradventure.models.courses.CourseCity;

import java.util.Arrays;

public class CheckCourseCityValidator implements ConstraintValidator<pl.uwm.wateradventure.models.validators.CourseCity, String> {

    @Override
    public boolean isValid(String givenValue, ConstraintValidatorContext constraintValidatorContext) {
        return Arrays.stream(CourseCity.values())
                .anyMatch(city -> city.enumValue.equals(givenValue));
    }


}
