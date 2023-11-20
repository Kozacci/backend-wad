package pl.uwm.wateradventure.models.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pl.uwm.wateradventure.models.events.EventType;

import java.util.Arrays;

public class CheckEventTypeValidator implements ConstraintValidator<pl.uwm.wateradventure.models.validators.EventType, String> {

    @Override
    public boolean isValid(String givenValue, ConstraintValidatorContext constraintValidatorContext) {
        return Arrays.stream(EventType.values())
                .anyMatch(type -> type.enumValue.equals(givenValue));
    }

}
