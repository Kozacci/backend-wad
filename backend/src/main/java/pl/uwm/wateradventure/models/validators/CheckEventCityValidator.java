package pl.uwm.wateradventure.models.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pl.uwm.wateradventure.models.events.EventCity;

import java.util.Arrays;

public class CheckEventCityValidator implements ConstraintValidator<pl.uwm.wateradventure.models.validators.EventCity, String> {

    @Override
    public boolean isValid(String givenValue, ConstraintValidatorContext constraintValidatorContext) {
        return Arrays.stream(EventCity.values())
                .anyMatch(city -> city.enumValue.equals(givenValue));
    }


}
