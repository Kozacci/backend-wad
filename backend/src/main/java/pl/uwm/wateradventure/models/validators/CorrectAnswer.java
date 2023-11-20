package pl.uwm.wateradventure.models.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Constraint(validatedBy = CheckCorrectAnswerValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface CorrectAnswer {

    String message() default "Correct answer must be one of: 'Odpowiedź A', 'Odpowiedź B', 'Odpowiedź C'";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
