package pl.uwm.wateradventure.models.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Constraint(validatedBy = CheckCategoryValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface Category {

    String message() default "The question category must be one of the fields: 'Przepisy', 'Podstawy locji', " +
            "'Wiadomości z zakresu meteorologii', 'Podstawy budowy jachtów motorowodnych', " +
            "'Silniki i układy napędowe', 'Wiadomości z zakresu ratownictwa wodnego'," +
            "'Pomoce nawigacyjne', 'Ochrona wód przed zanieczyszczeniem', " +
            "'Podstawowe przepisy prawa drogi na morskich i śródlądowych drogach wodnych', 'Teoria żeglowania'.";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
