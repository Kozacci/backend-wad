package pl.uwm.wateradventure.models.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pl.uwm.wateradventure.models.questions.CorrectAnswer;

import java.util.Arrays;

public class CheckCorrectAnswerValidator implements ConstraintValidator<pl.uwm.wateradventure.models.validators.CorrectAnswer, String> {
    @Override
    public boolean isValid(String givenValue, ConstraintValidatorContext constraintValidatorContext) {
        return Arrays.stream(CorrectAnswer.values())
                .anyMatch(correctAnswer -> correctAnswer.enumValue.equals(givenValue));
    }
}
