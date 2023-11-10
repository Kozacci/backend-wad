package pl.uwm.wateradventure.models.questions;

import pl.uwm.wateradventure.exceptions.custom_exceptions.InvalidQuestionCorrectAnswerException;

import java.util.stream.Stream;

public enum CorrectAnswer {

    FIRST_ANSWER("Odpowiedź A"),
    SECOND_ANSWER("Odpowiedź B"),
    THIRD_ANSWER("Odpowiedź C");

    public final String enumValue;

    CorrectAnswer(String enumValue) {
        this.enumValue = enumValue;
    }

    public static CorrectAnswer getCorrectAnswer(String givenValue) {
        return Stream.of(values())
                .filter(correctAnswer -> correctAnswer.enumValue.equals(givenValue))
                .findFirst()
                .orElseThrow(InvalidQuestionCorrectAnswerException::new);
    }
}
