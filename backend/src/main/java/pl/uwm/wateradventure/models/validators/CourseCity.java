package pl.uwm.wateradventure.models.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Constraint(validatedBy = CheckCourseCityValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCity {

    String message() default "Miasto kursu musi być jednym z pól: 'Sopot' lub 'Olecko'.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
