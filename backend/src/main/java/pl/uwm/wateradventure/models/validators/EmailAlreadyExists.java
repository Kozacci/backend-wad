package pl.uwm.wateradventure.models.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Constraint(validatedBy = CheckIfEmailAlreadyExistsValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailAlreadyExists {

    String message() default "jest już zarezerwowane";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
