package pl.uwm.wateradventure.models.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Constraint(validatedBy = CheckCourseTypeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseType {

    String message() default "Course type must be one of: 'Sternik motorowodny', " +
            "'Motorowodny sternik morski', 'Holowanie narciarza i obiektow nawodnych', " +
            "'Żeglarz jachtowy', 'Jachtowy sternik morski'.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
