package pl.uwm.wateradventure.models.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Constraint(validatedBy = CheckEventTypeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventType {

    String message() default "Event type must be one of: " +
            "'Rejs widokowy', 'Wieczór panieński', 'Wieczór kawalerski'" +
            ", 'Wynajem skutera wodnego' and 'Event dla firmy'.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
