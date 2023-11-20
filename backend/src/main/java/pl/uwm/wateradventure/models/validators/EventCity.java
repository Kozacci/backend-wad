package pl.uwm.wateradventure.models.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Constraint(validatedBy = CheckEventCityValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventCity {

    String message() default "Event city must be one of: 'Sopot', 'Olecko' and 'Gdańsk'.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
